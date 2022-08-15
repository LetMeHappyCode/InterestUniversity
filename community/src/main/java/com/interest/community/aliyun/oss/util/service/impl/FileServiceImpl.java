package com.interest.community.aliyun.oss.util.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.interest.community.aliyun.oss.util.OssPreperties;
import com.interest.community.aliyun.oss.util.service.FileService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(InputStream inputStream, String module, String originalFilename) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(OssPreperties.ENDPOINT, OssPreperties.KEY_ID, OssPreperties.KEY_SECRET);

        //判断oss实例是否存在：如果不存在则创建，如果存在则获取
        if(!ossClient.doesBucketExist(OssPreperties.BUCKET_NAME)){
            //创建bucket
            ossClient.createBucket(OssPreperties.BUCKET_NAME);
            //设置oss实例的访问权限：公共读
            ossClient.setBucketAcl(OssPreperties.BUCKET_NAME, CannedAccessControlList.PublicRead);
        }


        //构建日期路径：avatar/2019/02/26/文件名
        String folder = new DateTime().toString("yyyy/MM/dd");
        //文件名：uuid.扩展名
        String filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        //文件根路径
        String key = module + "/" + folder + "/" + filename;
        //上传
        ossClient.putObject(OssPreperties.BUCKET_NAME, key, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

        //阿里云文件绝对路径
        return "https://" + OssPreperties.BUCKET_NAME + "." + OssPreperties.ENDPOINT + "/" + key;
    }

    @Override
    public Boolean deleteOneFile(String fileUrl) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(OssPreperties.ENDPOINT, OssPreperties.KEY_ID, OssPreperties.KEY_SECRET);


        try {
            boolean found = ossClient.doesObjectExist(OssPreperties.BUCKET_NAME, fileUrl);
            System.out.println("文件是否存在"+found);
            // 删除文件或目录。如果要删除目录，目录必须为空。
            ossClient.deleteObject(OssPreperties.BUCKET_NAME, fileUrl);
            System.out.println("删除一个文件");
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
            return false;
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
            return false;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return true;
    }

    @Override
    public void deleteMultiFile(List<String> fileUrls) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(OssPreperties.ENDPOINT, OssPreperties.KEY_ID, OssPreperties.KEY_SECRET);

        try {
            // 删除文件。
            // 填写需要删除的多个文件完整路径。文件完整路径中不能包含Bucket名称。
            List<String> keys = new ArrayList<String>();
            keys.add("exampleobjecta.txt");
            keys.add("testfolder/sampleobject.txt");
            keys.add("exampleobjectb.txt");

            DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(OssPreperties.BUCKET_NAME).withKeys(fileUrls).withEncodingType("url"));
            List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
            try {
                for(String obj : deletedObjects) {
                    String deleteObj =  URLDecoder.decode(obj, "UTF-8");
                    System.out.println(deleteObj);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
