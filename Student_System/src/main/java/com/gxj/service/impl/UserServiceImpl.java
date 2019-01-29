package com.gxj.service.impl;

import com.gxj.mapper.UserMapper;
import com.gxj.pojo.Userlogin;
import com.gxj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void login(String username ,String password) {
        Userlogin userlogin = userMapper.selectUserByUsername(username);
        if(userlogin==null){
            throw new RuntimeException("没有此用户");
        }

        if(!userlogin.getPassword().equals(password)){
            throw new RuntimeException("登陆密码错误");
        }

    }
}
