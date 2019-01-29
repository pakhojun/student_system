package com.gxj.mapper;

import com.gxj.pojo.Userlogin;

public interface UserMapper {

    /**
     * 查询用户
     * @param username 用户名
     * @return
     */
    public Userlogin selectUserByUsername(String username);

}
