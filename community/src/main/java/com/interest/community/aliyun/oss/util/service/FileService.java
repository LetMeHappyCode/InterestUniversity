package com.interest.community.aliyun.oss.util.service;

import java.io.InputStream;
import java.util.List;


public interface FileService {
    //上传文件到阿里云
    String upload(InputStream inputStream, String module, String originalFilename);

    //删除一个文件
    Boolean deleteOneFile(String fileUrl);

    //删除多个文件
    void  deleteMultiFile(List<String> fileUrls);

}
