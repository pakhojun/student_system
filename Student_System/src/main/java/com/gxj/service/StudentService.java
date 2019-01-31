package com.gxj.service;

import com.gxj.pojo.Student;
import com.gxj.vo.PageBean;
import com.gxj.vo.StudentVo;

import java.util.List;

public interface StudentService {

    public List<StudentVo> findAll();

    public PageBean findListByLimit(Integer currentPage, Integer pageSize,String findByName);

    public void insert(Student student);

    public void update(Student student);

    public StudentVo findOne(int id);

    public void delete(int id);
}
