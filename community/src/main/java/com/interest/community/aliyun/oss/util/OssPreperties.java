package com.interest.community.aliyun.oss.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 注入yml属性到Oss实体类(是一个bean)
 * 实现InitializingBean接口，bean周期，在bean生成前将配置文件赋值到该组件
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssPreperties implements InitializingBean {
    private String endpoint;
    private String keyId;
    private String keySecret;
    private String bucketName;

    public static String ENDPOINT;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT = endpoint;
        KEY_ID = keyId;
        KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
