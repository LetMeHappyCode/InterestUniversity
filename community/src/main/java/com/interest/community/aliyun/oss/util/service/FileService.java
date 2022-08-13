package com.interest.community.aliyun.oss.util.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;


public interface FileService {
    String upload(InputStream inputStream, String module, String originalFilename);
}
