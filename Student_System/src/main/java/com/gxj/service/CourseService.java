package com.gxj.service;

import com.gxj.pojo.Course;
import com.gxj.vo.PageBean;

import java.util.List;

public interface CourseService {

    public List<Course> findList();

    public PageBean findListByLimit(Integer currentPage, Integer pageSize);

    public PageBean findListByLimit(Integer currentPage, Integer pageSize,String findByName);
}