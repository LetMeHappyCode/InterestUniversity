package com.interest.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.interest.security.mapper.UserMapper;
import com.interest.security.pojo.User;
import com.interest.security.service.UserService;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-08-31 11:00:05
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public User selectByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }
}




