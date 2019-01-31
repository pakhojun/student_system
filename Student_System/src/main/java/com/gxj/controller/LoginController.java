package com.gxj.controller;

import com.gxj.pojo.Userlogin;
import com.gxj.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public String login(Userlogin userlogin) throws Exception {

        UsernamePasswordToken token=new UsernamePasswordToken(userlogin.getUsername(),userlogin.getPassword());

        Subject subject = SecurityUtils.getSubject();

        subject.login(token);

        if(subject.hasRole("admin")){
            System.out.println("我有admin权限");
        }

        userService.login(userlogin.getUsername(), userlogin.getPassword());
        return "redirect:/course/findList";

    }


}
