package com.interest.security.mapper;

import com.interest.security.pojo.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Administrator
* @description 针对表【permission】的数据库操作Mapper
* @createDate 2022-08-31 15:53:13
* @Entity com.interest.security.pojo.Permission
*/
public interface PermissionMapper extends BaseMapper<Permission> {

    List<String> selectPermissionValueByUserId(String userid);

    List<String> selectAllPermissionValue();
}




