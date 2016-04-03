package com.yk.model;

import java.io.Serializable;

/**
 * Created by dylanyang on 12/8/15.
 */
public class CourseEntity implements Serializable{
    private static final long serialVersionUID = 5141743852783791468L;
    private int id;
    private String course_num;
    private String course_name;
    private int credit;

    public CourseEntity() {

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse_num() {
        return course_num;
    }

    public void setCourse_num(String course_num) {
        this.course_num = course_num;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "id=" + id +
                ", course_num='" + course_num + '\'' +
                ", course_name='" + course_name + '\'' +
                ", credit=" + credit +
                '}';
    }
}
