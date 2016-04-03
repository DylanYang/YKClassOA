package com.yk.model;

import java.io.Serializable;

/**
 * Created by dylanyang on 12/8/15.
 */
public class SelectedCourseEntity implements Serializable{
    private static final long serialVersionUID = 4003873165779091950L;
    private int id;
    private String stu_num;
    private String ord_score;
    private String end_score;
    private String final_result;
    private CourseEntity course_id;
    private StudentEntity stu_id;

    public SelectedCourseEntity() {

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

    public String getStu_num() {
        return stu_num;
    }

    public void setStu_num(String stu_num) {
        this.stu_num = stu_num;
    }

    public String getOrd_score() {
        return ord_score;
    }

    public void setOrd_score(String ord_score) {
        this.ord_score = ord_score;
    }

    public String getEnd_score() {
        return end_score;
    }

    public void setEnd_score(String end_score) {
        this.end_score = end_score;
    }

    public String getFinal_result() {
        return final_result;
    }

    public void setFinal_result(String final_result) {
        this.final_result = final_result;
    }

    public CourseEntity getCourse_id() {
        return course_id;
    }

    public void setCourse_id(CourseEntity course_id) {
        this.course_id = course_id;
    }

    public StudentEntity getStu_id() {
        return stu_id;
    }

    public void setStu_id(StudentEntity stu_id) {
        this.stu_id = stu_id;
    }

    @Override
    public String toString() {
        return "SelectedCourseEntity{" +
                "id=" + id +
                ", stu_num='" + stu_num + '\'' +
                ", ord_score='" + ord_score + '\'' +
                ", end_score='" + end_score + '\'' +
                ", final_result='" + final_result + '\'' +
                ", course_id=" + course_id +
                ", stu_id=" + stu_id +
                '}';
    }
}
