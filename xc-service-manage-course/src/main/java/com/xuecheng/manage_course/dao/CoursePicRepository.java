package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.CoursePic;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CoursePicRepository extends JpaRepository<CoursePic, String> {
    /**
     * 返回值大于0说明删除成功，表示删除成功的记录数
     *
     * @param courseId
     * @return
     */
    long deleteByCourseid(String courseId);
}
