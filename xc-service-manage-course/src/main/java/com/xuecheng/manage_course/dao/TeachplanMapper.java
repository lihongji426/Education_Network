package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeachplanMapper {
    /**
     * 课程计划查询
     *
     * @param courseId
     * @return
     */
    public TeachplanNode selectList(String courseId);
}
