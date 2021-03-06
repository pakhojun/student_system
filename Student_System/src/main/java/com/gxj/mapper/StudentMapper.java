package com.gxj.mapper;

import com.gxj.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {


    public List<Student> findAll();

    public int count(@Param("findByName") String findByName);

    public List<Student> findListByLimit(@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize,@Param("findByName") String findByName);

    public void insert(Student student);

    public void update(Student student);

    public Student findOne(int id);

    public void delete(int id);
}
