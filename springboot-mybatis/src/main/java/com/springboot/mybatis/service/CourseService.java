package com.springboot.mybatis.service;

import com.springboot.mybatis.entity.Course;
import com.springboot.mybatis.entity.CourseVO;

import java.util.List;

public interface CourseService {
    List<Course> selectAll();

    List<CourseVO> selectI();

    List<CourseVO> selectF();

    Course getOne(long courseId);

    void delete(long courseId);

    void insert(Course course);

    void update(Course course);

    List<CourseVO> selectCurrentCourses();

}
