package com.gxj.controller;

import com.gxj.pojo.Userlogin;
import com.gxj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public String login(Userlogin userlogin) throws Exception{
            userService.login(userlogin.getUsername(),userlogin.getPassword());
            return "redirect:/course/findList";

    }


}
