package com.gxj.service.impl;

import com.gxj.mapper.TeacherMapper;
import com.gxj.pojo.Teacher;
import com.gxj.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<Teacher> findList() {
        return teacherMapper.findList();
    }
}
