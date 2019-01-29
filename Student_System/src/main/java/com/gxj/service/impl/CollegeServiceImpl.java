package com.gxj.service.impl;

import com.gxj.mapper.CollegeMapper;
import com.gxj.pojo.College;
import com.gxj.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public List<College> findList() {
        return collegeMapper.findList();
    }
}
