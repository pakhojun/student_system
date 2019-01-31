package com.gxj.service.impl;

import com.gxj.mapper.CollegeMapper;
import com.gxj.mapper.StudentMapper;
import com.gxj.pojo.College;
import com.gxj.pojo.Student;
import com.gxj.service.StudentService;
import com.gxj.vo.PageBean;
import com.gxj.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public List<StudentVo> findAll() {
        List<StudentVo> studentVoList=new ArrayList<>();
        List<Student> studentList = studentMapper.findAll();
        for (Student student:studentList){
            College college= collegeMapper.findOne(student.getCollegeid());
            StudentVo studentVo=new StudentVo();
            studentVo.setStudent(student);
            studentVo.setCollege(college);

            studentVoList.add(studentVo);
        }

        return studentVoList;
    }

    @Override
    public PageBean findListByLimit(Integer currentPage, Integer pageSize,String findByName) {
        PageBean pageBean=new PageBean(currentPage,pageSize);

        List<StudentVo> studentVoList=new ArrayList<>();

        int count = studentMapper.count(findByName);
        pageBean.setTotalCount(count);
        List<Student> studentList = studentMapper.findListByLimit(pageBean.getStartIndex(), pageBean.getPageSize(),findByName);
        for (Student student:studentList){
            College college= collegeMapper.findOne(student.getCollegeid());
            StudentVo studentVo=new StudentVo();
            studentVo.setStudent(student);
            studentVo.setCollege(college);

            studentVoList.add(studentVo);
        }

        pageBean.setData(studentVoList);

        return pageBean;
    }

    @Override
    public void insert(Student student) {
        studentMapper.insert(student);
    }

    @Override
    public void update(Student student) {
        studentMapper.update(student);
    }

    @Override
    public StudentVo findOne(int id) {
        Student student = studentMapper.findOne(id);
        College college = collegeMapper.findOne(student.getCollegeid());
        StudentVo studentVo=new StudentVo();
        studentVo.setStudent(student);
        studentVo.setCollege(college);
        return studentVo;
    }

    @Override
    public void delete(int id) {
        studentMapper.delete(id);
    }


}
