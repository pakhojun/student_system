package com.gxj.controller;

import com.gxj.pojo.College;
import com.gxj.pojo.Student;
import com.gxj.service.CollegeService;
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

    @Autowired
    private CollegeService collegeService;

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


    @RequestMapping("/addStudent")
    public String showAddStudentPage(Model model){
        List<College> collegeList = collegeService.findList();
        model.addAttribute("collegeList",collegeList);
        return "/admin/addStudent";
    }

    @RequestMapping("/addStudentInfo")
    public String addStudent(Student student){
        studentService.insert(student);
        return "redirect:/student/findList";
    }


    @RequestMapping("/editStudent")
    public String showEditPage(int id,Model model){
        StudentVo studentVo = studentService.findOne(id);
        List<College> collegeList = collegeService.findList();
        model.addAttribute("studentVo",studentVo);
        model.addAttribute("collegeList",collegeList);
        return "/admin/editStudent";
    }

    @RequestMapping("/editStudentInfo")
    public String editStudent(Student student){
        studentService.update(student);
        return "redirect:/student/findList";
    }

    @RequestMapping("/removeStudent")
    public String  removeStudent(int id){
        studentService.delete(id);
        return "redirect:/student/findList";
    }
}
