package com.gxj.service;

import com.gxj.pojo.Course;
import com.gxj.vo.PageBean;

import java.util.List;

public interface CourseService {

    public List<Course> findList();

    public PageBean findListByLimit(Integer currentPage, Integer pageSize);

    public PageBean findListByLimit(Integer currentPage, Integer pageSize,String findByName);

    public void insert(Course course);

    public Course findOne(Integer id);

    public void update(Course course);

    public void delete(Integer id);
}
