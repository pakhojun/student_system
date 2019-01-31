package com.gxj.controller;

import com.gxj.pojo.College;
import com.gxj.pojo.Course;
import com.gxj.pojo.Teacher;
import com.gxj.service.CollegeService;
import com.gxj.service.CourseService;
import com.gxj.service.TeacherService;
import com.gxj.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CollegeService collegeService;

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



    @RequestMapping("/addCourse")
    public String showAddPage(Model model){
        List<Teacher> teacherList = teacherService.findList();
        List<College> collegeList = collegeService.findList();
        model.addAttribute("teacherList",teacherList);
        model.addAttribute("collegeList",collegeList);
        return "admin/addCourse";
    }

    @RequestMapping("/editCourse")
    public String editCourse(Integer id,Model model){
        Course course = courseService.findOne(id);
        model.addAttribute("course",course);
        List<Teacher> teacherList = teacherService.findList();
        List<College> collegeList = collegeService.findList();
        model.addAttribute("teacherList",teacherList);
        model.addAttribute("collegeList",collegeList);
        return "admin/editCourse";
    }


    @RequestMapping("/{page}")
    public String showPage(@PathVariable("page") String page){
        return "admin/"+page;
    }

    @RequestMapping("/addCourseInfo")
    public String addCourseInfo(Course course){
        try {
            courseService.insert(course);
            return "redirect:/course/findList";
        }catch (Exception e){
            e.printStackTrace();
            return "/error";
        }
    }

    @RequestMapping("/editCourseInfo")
    public String editCourseInfo(Course course){
        courseService.update(course);
        return "redirect:/course/findList";
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Map delete(Integer id){
        Map map=new HashMap();
        try {
            courseService.delete(id);
            map.put("message","删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message","删除失败");
        }


        return map;
    }

}
