package com.gxj.controller;

import com.gxj.pojo.Student;
import com.gxj.service.StudentService;
import com.gxj.vo.PageBean;
import com.gxj.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /*@RequestMapping("/findList")
    public String findList(Model model){
        List<StudentVo> studentVoList = studentService.findAll();

        model.addAttribute("studentVoList",studentVoList);
        return "/admin/showStudent";
    }
*/
    @RequestMapping(value = "/findList",method = RequestMethod.GET)
    public String findList1(Model model, @RequestParam(defaultValue = "1") Integer currentPage,
                           @RequestParam(defaultValue = "5") Integer pageSize,String findByName) throws UnsupportedEncodingException {
        if(findByName!=null){
            findByName=new String(findByName.getBytes("iso-8859-1"),"utf-8");
        }

        PageBean pageBean = studentService.findListByLimit(currentPage, pageSize,findByName);

        model.addAttribute("pageBean",pageBean);
        model.addAttribute("findByName",findByName);
        return "/admin/showStudent";
    }

    @RequestMapping(value = "/findList",method = RequestMethod.POST)
    public String findList2(Model model, @RequestParam(defaultValue = "1") Integer currentPage,
                           @RequestParam(defaultValue = "5") Integer pageSize,String findByName) throws UnsupportedEncodingException {

        PageBean pageBean = studentService.findListByLimit(currentPage, pageSize,findByName);

        model.addAttribute("pageBean",pageBean);
        model.addAttribute("findByName",findByName);
        return "/admin/showStudent";
    }

}