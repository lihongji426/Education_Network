package com.xuecheng.framework.domain.course.ext;


import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.domain.course.CoursePic;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseView implements java.io.Serializable {

    private CourseBase courseBase;

    private CoursePic coursePic;

    private CourseMarket courseMarket;

    private TeachplanNode teachplanNode;
}
