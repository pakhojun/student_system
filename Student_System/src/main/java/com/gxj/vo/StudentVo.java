package com.gxj.vo;

import com.gxj.pojo.College;
import com.gxj.pojo.Student;

public class StudentVo {

    private Student student;

    private College college;


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }
}
