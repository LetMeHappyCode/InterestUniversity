package com.interest.community.aliyun.oss.util.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.interest.community.aliyun.oss.util.OssPreperties;
import com.interest.community.aliyun.oss.util.service.FileService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.io.InputStream;
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
}
