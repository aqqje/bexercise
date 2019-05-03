package cn.fileupload;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    public static final String FILESERVICEURL = "http://localhost:9090/imgupload/upload/";
    /**
     * 文件上传传统方式
     * @param request
     */
    @RequestMapping(value = "/fileUpload1.do", method = RequestMethod.POST)
    public String fileUpload(HttpServletRequest request){
        System.out.println("文件上传传统方式.............");
        // 获取上传路径
        String realPath = request.getSession().getServletContext().getRealPath("/uploads/");
        File file = new File(realPath);
        // 判断路径是否存在
        if(!file.exists()){
            // 不存在路径则新建
            file.mkdirs();
        }
        System.out.println(realPath);
        // 解析 request 对象, 获取上传文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 解析 request
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            for (FileItem item: fileItems){
                // 获取上传文件项
                if(!item.isFormField()){
                    String fileName = item.getName();
                    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                    fileName = uuid+"_"+fileName;
                    // 完成上传
                    System.out.println(realPath);
                    item.write(new File(realPath, fileName));
                    // 删除临时文件
                    item.delete();
                    return "success";
                }
            }           

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "update";
    }


    /**
     * spring mvc 文件上传方式
     * @param request
     */
    @RequestMapping(value = "/fileUpload2.do", method = RequestMethod.POST)
    public String fileUpload2(HttpServletRequest request, MultipartFile uploat){
        System.out.println("spring mvc 文件上传方式.............");
        // 获取上传路径
        String realPath = request.getSession().getServletContext().getRealPath("/uploads/");
        File file = new File(realPath);
        // 判断路径是否存在
        if(!file.exists()){
            // 不存在路径则新建
            file.mkdirs();
        }
        // 获取文件名
        String filename = uploat.getOriginalFilename();
        // 上传文件
        try {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            filename = uuid+"_"+filename;
            uploat.transferTo(new File(realPath, filename));
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "update";
    }

    /**
     * 跨服务器文件上传
     * @param picname
     * @param uploadFile
     * @return
     */
    @RequestMapping(value = "/fileUpload3.do")
    public String fileUpload3(String picname, MultipartFile uploadFile) throws IOException {


        String encodeFileName = URLEncoder.encode(picname, "utf-8");
        String fileName = "";
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        /*文件原名*/
        String uploadName = URLEncoder.encode(uploadFile.getOriginalFilename(), "utf-8");
        /*文件扩展名*/
        String extendName = uploadName.substring(uploadName.lastIndexOf(".") + 1, uploadName.length());
        /*获取文件名*/
        if(encodeFileName != null && !"".equals(encodeFileName) && !"null".equals(encodeFileName)){
            fileName = uuid + "_" + encodeFileName + "." + extendName;
        }else{
            fileName = uuid + "_" + uploadName;
        }
        System.out.println("文件地址:"+FILESERVICEURL+fileName);
        /*创建 sun 公司提供的 jersey 包中的 Client 对象*/
        Client client = Client.create();
        /*指定上传文件的地址，该地址是 web 路径*/
        WebResource resource = client.resource(FILESERVICEURL + fileName);
        /*实现上传*/
        String result = resource.put(String.class, uploadFile.getBytes());
        System.out.println(result);
        return "success";
    }
}
