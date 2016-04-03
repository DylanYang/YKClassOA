package com.yk.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by dylanyang on 12/10/15.
 */
public class ClassItemEntity implements Serializable{
    private static final long serialVersionUID = 1475471976177168206L;
    private int id;
    private String classNo;
    private int year;
    private TeacherEntity teacher_id;
    private Set<StudentEntity> setStudents;

    public ClassItemEntity() {
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

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public TeacherEntity getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(TeacherEntity teacher_id) {
        this.teacher_id = teacher_id;
    }

    public Set<StudentEntity> getSetStudents() {
        return setStudents;
    }

    public void setSetStudents(Set<StudentEntity> setStudents) {
        this.setStudents = setStudents;
    }

    @Override
    public String toString() {
        return "ClassItemEntity{" +
                "id=" + id +
                ", classNo='" + classNo + '\'' +
                ", year=" + year +
                ", teacher_id=" + teacher_id +
                ", setStudent=" + setStudents +
                '}';
    }
}
