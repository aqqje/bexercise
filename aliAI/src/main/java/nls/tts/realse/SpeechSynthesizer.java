package nls.tts.realse;

import com.alibaba.nls.client.AccessToken;
import com.alibaba.nls.client.protocol.NlsClient;
import com.alibaba.nls.client.protocol.OutputFormatEnum;
import com.alibaba.nls.client.protocol.SampleRateEnum;
import com.alibaba.nls.client.protocol.tts.SpeechSynthesizerListener;
import com.alibaba.nls.client.protocol.tts.SpeechSynthesizerResponse;
import nls.tts.utils.textUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.List;

/** 文本语言合成 */
public class SpeechSynthesizer {
    private static final Logger logger = LoggerFactory.getLogger(SpeechSynthesizer.class);
    private static final String APP_KEY = "pO3ZOy1aMlReV7dH";
    private static final String ID = "LTAI4GDi7mb52tT77MqW2B9V";
    private static final String SECRET = "OkmREGU6Pmj7UR4DvvuH3SLq6yy3zR";
    private static final String URL = "wss://nls-gateway.cn-shanghai.aliyuncs.com/ws/v1";
    private static NlsClient client;
    private static long startTime;
    private static long expireTime = 0L;

    public static NlsClient createClient() {
        // 判断是否已经过期，如果过期则再创建
        if((expireTime * 1000) > System.currentTimeMillis()){return client; }

        AccessToken accessToken = new AccessToken(ID, SECRET);
        try {
            accessToken.apply();
            System.out.println("get token: " + accessToken.getToken() + ", expire time: " + accessToken.getExpireTime());
            expireTime = accessToken.getExpireTime();
            return new NlsClient(URL, accessToken.getToken());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param text 需要生成的音频的文本
     * @param fileName 音频文件名
     * @param directory 音频生成目录
     */
    public static void process(String text, String fileName, String directory) {
        com.alibaba.nls.client.protocol.tts.SpeechSynthesizer synthesizer = null;
        try {
            //创建实例,建立连接
            synthesizer = new com.alibaba.nls.client.protocol.tts.SpeechSynthesizer(client, getSynthesizerListener(fileName, directory));
            synthesizer.setAppKey(APP_KEY);
            //设置返回音频的编码格式
            synthesizer.setFormat(OutputFormatEnum.MP3);
            //设置返回音频的采样率
            synthesizer.setSampleRate(SampleRateEnum.SAMPLE_RATE_16K);
            //发音人  男-Sicheng 男-Aida女-Siqi 女-Aiqi 女-Siyue 66 女-Aiyue 女-Yina 女-AiXia
            synthesizer.setVoice("Sicheng");
            // 设置声音大小
            synthesizer.setVolume(200);
            //语调，范围是-500~500，可选，默认是0
            synthesizer.setPitchRate(0);
            //语速，范围是-500~500，默认是0
            synthesizer.setSpeechRate(-100);
            //设置用于语音合成的文本
            synthesizer.setText(text);
            // 是否开启字幕功能(返回对应文本的相应时间戳)，默认不开启，需要注意并非所有发音人都支持该参数
            synthesizer.addCustomedParam("enable_subtitle", true);
            //此方法将以上参数设置序列化为json发送给服务端,并等待服务端确认
            long start = System.currentTimeMillis();
            synthesizer.start();
            logger.info("tts start latency " + (System.currentTimeMillis() - start) + " ms");
            SpeechSynthesizer.startTime = System.currentTimeMillis();
            //等待语音合成结束
            synthesizer.waitForComplete();
            logger.info("tts stop latency " + (System.currentTimeMillis() - start) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            if (null != synthesizer) {
                synthesizer.close();
            }
        }
    }
    private static SpeechSynthesizerListener getSynthesizerListener(String fileName, String directory) {

        // 判断生成音频目录是否存在
        File dir = new File(fileName);
        if(!dir.exists()){dir.mkdirs();}
        SpeechSynthesizerListener listener = null;

        // 生成音频的绝对地址
        String absFileURL = (directory + "\\" +fileName + "." + OutputFormatEnum.MP3).replaceAll("\\+", "\\").replaceAll("/+", "/");

        // 删除可能之前存在的文件，避免重复写入
        File file= new File(absFileURL);
        if(file.exists()){file.delete();}

        try {
            listener = new SpeechSynthesizerListener() {
                File f=new File(absFileURL);

                FileOutputStream fout = new FileOutputStream(f, true);
                private boolean firstRecvBinary = true;
                //语音合成结束
                @Override
                public void onComplete(SpeechSynthesizerResponse response) {
                    // TODO 当onComplete时表示所有TTS数据已经接收完成，因此这个是整个合成延迟，该延迟可能较大，未必满足实时场景
                    System.out.println("name: " + response.getName() +
                            ", status: " + response.getStatus()+
                            ", output file :"+f.getAbsolutePath()
                    );
                }
                //语音合成的语音二进制数据
                @Override
                public void onMessage(ByteBuffer message) {
                    try {
                        if(firstRecvBinary) {
                            // TODO 此处是计算首包语音流的延迟，收到第一包语音流时，即可以进行语音播放，以提升响应速度(特别是实时交互场景下)
                            firstRecvBinary = false;
                            long now = System.currentTimeMillis();
                            logger.info("tts first latency : " + (now - SpeechSynthesizer.startTime) + " ms");
                        }
                        byte[] bytesArray = new byte[message.remaining()];
                        message.get(bytesArray, 0, bytesArray.length);
                        fout.write(bytesArray);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFail(SpeechSynthesizerResponse response){
                    // TODO 重要提示： task_id很重要，是调用方和服务端通信的唯一ID标识，当遇到问题时，需要提供此task_id以便排查
                    System.out.println(
                            "task_id: " + response.getTaskId() +
                                    //状态码 20000000 表示识别成功
                                    ", status: " + response.getStatus() +
                                    //错误信息
                                    ", status_text: " + response.getStatusText());
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listener;
    }

    public static void shutdown() {
        client.shutdown();
    }

    public static void generateTTS(String text, String fileName, String directory){
        List<String> texts = textUtils.breakDown(text, 300);
        for (int i = 0; i < texts.size(); i++) {
            System.out.println("正在处理第^_^"+(i+1)+"^_^节文本");
            client = createClient();
            process(texts.get(i),fileName, directory);
        }
        shutdown();
    }

    public static void generateFileTTS(String fileUrl, String fileName, String directory){
        String text = textUtils.textFileRead(fileUrl);
        List<String> texts = textUtils.breakDown(text, 300);
        for (int i = 0; i < texts.size(); i++) {
            System.out.println("正在处理第^_^"+(i+1)+"^_^节文本");
            client = createClient();
            process(texts.get(i),fileName, directory);
        }
        shutdown();
    }

    public static void main(String[] args) throws FileNotFoundException {
        generateFileTTS("C:\\AqqJe\\自媒体\\庆余年第二季\\剪辑\\第五集\\CHS_2-5_文稿下.txt","庆余年第五集下音频" , "C:\\AqqJe\\自媒体\\庆余年第二季\\剪辑\\第五集");
    }
}
