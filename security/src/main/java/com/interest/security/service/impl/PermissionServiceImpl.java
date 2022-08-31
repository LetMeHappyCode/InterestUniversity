package com.interest.security.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.interest.security.pojo.Permission;
import com.interest.security.service.PermissionService;
import com.interest.security.mapper.PermissionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Administrator
* @description 针对表【permission】的数据库操作Service实现
* @createDate 2022-08-31 15:53:13
*/
@Service
@Slf4j
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

    @Override
    public List<String> selectPermissionValueByUserId(String userid) {
        List<String> selectPermissionValueList = null;
        log.info("查询权限");
        List<String> permissionList = baseMapper.selectPermissionValueByUserId(userid);
        if (permissionList==null){
            selectPermissionValueList.add("test");
        }
        System.out.println("selectPermissionValueList的值"+selectPermissionValueList.get(0));
        System.out.println(selectPermissionValueList);
        return  selectPermissionValueList;
    }
}




