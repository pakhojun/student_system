package com.gxj.service.impl;

import com.gxj.mapper.CourseMapper;
import com.gxj.pojo.Course;
import com.gxj.service.CourseService;
import com.gxj.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findList() {
        return courseMapper.findList();
    }

    @Override
    public PageBean findListByLimit(Integer currentPage, Integer pageSize) {
        PageBean pageBean=new PageBean(currentPage,pageSize);
        Integer count = courseMapper.count(null);
        pageBean.setTotalCount(count);
        List<Course> listByLimit = courseMapper.findListByLimit(pageBean.getStartIndex(), pageBean.getPageSize());
        pageBean.setData(listByLimit);
        return pageBean;
    }

    @Override
    public PageBean findListByLimit(Integer currentPage, Integer pageSize, String findByName) {
        PageBean pageBean=new PageBean(currentPage,pageSize);
        Integer count = courseMapper.count(findByName);
        pageBean.setTotalCount(count);
        List<Course> listByLimit = courseMapper.findListByLimitAndName(pageBean.getStartIndex(), pageBean.getPageSize(),findByName);
        pageBean.setData(listByLimit);
        return pageBean;
    }

    @Override
    public void insert(Course course) {
        courseMapper.insert(course);
    }

    @Override
    public Course findOne(Integer id) {
        return courseMapper.findOne(id);
    }

    @Override
    public void update(Course course) {
        courseMapper.update(course);
    }

    @Override
    public void delete(Integer id) {
        courseMapper.delete(id);
    }
}
