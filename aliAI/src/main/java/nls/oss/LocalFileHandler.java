package nls.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LocalFileHandler {
    /** 存储点*/
    private static final String endpoint = "oss-accelerate.aliyuncs.com";
    /** 用户ID */
    private static final String accessKeyId = "LTAI4G7eFQAdNQVFTWVbXhtr";
    /** 用户密码 */
    private static final String accessKeySecret = "uwzoyeZTHRc5hjlvFAdnEjs99ZNkUF";
    /** 存储仓库名*/
    private static final String bucketName = "aqqje-oss";

    private static OSS ossClient;

    static{ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);}

    /** 阿里 OSS 本地文件上传 */
    public static void doUpload(String bucktFileUrl,String absFileUrl){
        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, bucktFileUrl, new File(absFileUrl));
//        ObjectMetadata objectMetadata = new ObjectMetadata();
//        objectMetadata.setHeader();
//        putObjectRequest.setMetadata(x-oss-forbid-overwrite);
        // 上传文件。
        ossClient.putObject(putObjectRequest);
        // 关闭OSSClient。
        ossClient.shutdown();
    }
    /** 阿里 OSS 存储文件删除(物理) */
    public static void removeOnlineFileByVersionId(String objectName, String versionId){
        // 可以传入文件版本id，也可以传入删除标记的版本id。
        String versionid = "<yourObjectVersionidOrDelMarkerVersionid>";
        // 删除指定版本的object。
        ossClient.deleteVersion(bucketName, objectName , versionid);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /** 阿里 OSS 存储文件删除(逻辑) */
    public void remobeOnlineFileDoFlag(String bucketName, String objectName ){
        // 开启版本控制状态下，此方法为临时删除，将会给object添加删除标记。
        ossClient.deleteObject(bucketName, objectName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /** 阿里 OSS 存储文件删除指定前缀 */
    public static void remoberOnlineFileByPrefix(String filePrefix){

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 列举所有指定前缀的文件的版本信息并删除。
        String nextKeyMarker = null;
        String nextVersionMarker = null;
        VersionListing versionListing = null;
        do {
            ListVersionsRequest listVersionsRequest = new ListVersionsRequest()
                    .withBucketName(bucketName)
                    .withKeyMarker(nextKeyMarker)
                    .withVersionIdMarker(nextVersionMarker)
                    .withPrefix(filePrefix);

            versionListing = ossClient.listVersions(listVersionsRequest);
            if (versionListing.getVersionSummaries().size() > 0) {
                List<DeleteVersionsRequest.KeyVersion> keyVersionsList = new ArrayList<DeleteVersionsRequest.KeyVersion>();
                for (OSSVersionSummary ossVersion : versionListing.getVersionSummaries()) {
                    System.out.println("key name: " + ossVersion.getKey());
                    System.out.println("versionid: " + ossVersion.getVersionId());
                    System.out.println("Is delete marker: " + ossVersion.isDeleteMarker());
                    keyVersionsList.add(new DeleteVersionsRequest.KeyVersion(ossVersion.getKey(), ossVersion.getVersionId()));
                }
                DeleteVersionsRequest delVersionsRequest = new DeleteVersionsRequest(bucketName).withKeys(keyVersionsList);
                ossClient.deleteVersions(delVersionsRequest);
            }

            nextKeyMarker = versionListing.getNextKeyMarker();
            nextVersionMarker = versionListing.getNextVersionIdMarker();
        } while (versionListing.isTruncated());
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /** 列举Bucket中所有Object的版本信息 */
    public static void ListAllObjectVersionInfo(){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 列举所有的Object的版本信息。
        String nextKeyMarker = null;
        String nextVersionMarker = null;
        VersionListing versionListing = null;
        do {
            ListVersionsRequest listVersionsRequest = new ListVersionsRequest()
                    .withBucketName(bucketName)
                    .withKeyMarker(nextKeyMarker)
                    .withVersionIdMarker(nextVersionMarker);

            versionListing = ossClient.listVersions(listVersionsRequest);
            for (OSSVersionSummary ossVersion : versionListing.getVersionSummaries()) {
                System.out.println("key name: " + ossVersion.getKey());
                System.out.println("versionid: " + ossVersion.getVersionId());
                System.out.println("Is latest: " + ossVersion.isLatest());
                System.out.println("Is delete marker: " + ossVersion.isDeleteMarker());
            }

            nextKeyMarker = versionListing.getNextKeyMarker();
            nextVersionMarker = versionListing.getNextVersionIdMarker();
        } while (versionListing.isTruncated());

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    public static void main(String[] args) {
        remoberOnlineFileByPrefix("test");
    }
}
