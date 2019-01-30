package com.gxj.mapper;

import com.gxj.pojo.College;
import com.gxj.pojo.Course;

import java.util.List;

public interface CollegeMapper {


    public List<College> findList();

    public College findOne(int id);
}
