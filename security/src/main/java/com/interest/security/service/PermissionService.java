package com.interest.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.interest.security.pojo.Permission;

import java.util.List;

/**
* @author Administrator
* @description 针对表【permission】的数据库操作Service
* @createDate 2022-08-31 15:53:13
*/
public interface PermissionService extends IService<Permission> {
    //根据用户id获取用户菜单
    List<String> selectPermissionValueByUserId(String userid);
}
