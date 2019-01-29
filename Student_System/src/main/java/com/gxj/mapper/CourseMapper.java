package com.gxj.mapper;

import com.gxj.pojo.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {

    /**
     * 查询所有的课程列表
     * @return
     */
    public List<Course> findList();

    /**
     * 查询总条数
     * @return
     */
    public Integer count(@Param("findByName") String findByName);

    /**
     * 分页查询
     * @param startIndex 开始索引
     * @param pageSize
     * @return
     */
    public List<Course> findListByLimit(@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);

    public List<Course> findListByLimitAndName(@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize,@Param("findByName") String findByName);


    public void insert(Course course);

    public Course findOne(Integer id);

    public void update(Course course);
}
