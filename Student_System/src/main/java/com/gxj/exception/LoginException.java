package com.gxj.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginException implements HandlerExceptionResolver {


    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse, Object o, Exception e) {

        String message = e.getMessage();

        ModelAndView modelAndView=new ModelAndView();

        modelAndView.addObject("message",message);

        modelAndView.setViewName("error");
        return modelAndView;
    }
}
