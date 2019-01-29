package com.gxj.controller;

import com.gxj.pojo.Course;
import com.gxj.service.CourseService;
import com.gxj.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;


    /*@RequestMapping("/findList")
    public String findList(Model model){
        List<Course> courseList = courseService.findList();
        model.addAttribute("courseList",courseList);
        return "/admin/showCourse";
    }*/

    @RequestMapping("/findList")
    public String findList(@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam(defaultValue = "5") Integer pageSize,
                           Model model,String findByName){
        PageBean pageBean =null;
        if(findByName!=null){
            pageBean=courseService.findListByLimit(currentPage,pageSize,findByName);
            model.addAttribute("findByName",findByName);
        }else{
            pageBean=courseService.findListByLimit(currentPage,pageSize);
        }
        model.addAttribute("courseList",pageBean.getData());
        model.addAttribute("pageBean",pageBean);
        return "/admin/showCourse";
    }



}
