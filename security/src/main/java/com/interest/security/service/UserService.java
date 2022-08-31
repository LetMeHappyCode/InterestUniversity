package com.interest.security.service;

import com.interest.security.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Service
* @createDate 2022-08-31 11:00:05
*/
public interface UserService extends IService<User> {

    User selectByUsername(String username);
}
