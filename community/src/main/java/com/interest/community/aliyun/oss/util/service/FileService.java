package com.interest.community.aliyun.oss.util.service;

import com.interest.common.base.result.R;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public interface FileService {
    //上传文件到阿里云
    String upload(InputStream inputStream, String module, String originalFilename);

    //删除一个文件
    R deleteOneFile(String fileUrl);

    //删除多个文件
    void  deleteMultiFile(List<String> fileUrls);

}
