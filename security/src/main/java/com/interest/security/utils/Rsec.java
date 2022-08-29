package com.interest.security.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

//统一返回结果的类
@Data
public class Rsec {

    private Boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<String, Object>();

    //把构造方法私有
    private Rsec() {}

    //成功静态方法
    public static Rsec ok() {
        Rsec r = new Rsec();
        r.setSuccess(true);
        r.setCode(20000);
        r.setMessage("成功");
        return r;
    }

    //失败静态方法
    public static Rsec error() {
        Rsec r = new Rsec();
        r.setSuccess(false);
        r.setCode(20001);
        r.setMessage("失败");
        return r;
    }

    public Rsec success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public Rsec message(String message){
        this.setMessage(message);
        return this;
    }

    public Rsec code(Integer code){
        this.setCode(code);
        return this;
    }

    public Rsec data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Rsec data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}