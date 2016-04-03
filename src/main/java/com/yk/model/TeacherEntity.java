package com.yk.model;

import java.io.Serializable;

/**
 * Created by dylanyang on 12/9/15.
 */
public class TeacherEntity implements Serializable{
    private static final long serialVersionUID = 5243886113860466130L;
    private int id;
    private String staffId;
    private AccountEntity account_id;
    private ClassItemEntity classItem_id;
    private CourseEntity course_id;

    public TeacherEntity() {

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

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public AccountEntity getAccount_id() {
        return account_id;
    }

    public void setAccount_id(AccountEntity account_id) {
        this.account_id = account_id;
    }

    public ClassItemEntity getClassItem_id() {
        return classItem_id;
    }

    public void setClassItem_id(ClassItemEntity classItem_id) {
        this.classItem_id = classItem_id;
    }

    public CourseEntity getCourse_id() {
        return course_id;
    }

    public void setCourse_id(CourseEntity course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "TeacherEntity{" +
                "id=" + id +
                ", staffId='" + staffId + '\'' +
                ", account_id=" + account_id +
                ", classItem_id=" + classItem_id +
                ", course_id=" + course_id +
                '}';
    }
}
