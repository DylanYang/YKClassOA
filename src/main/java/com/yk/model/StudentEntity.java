package com.yk.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by dylanyang on 10/11/15.
 */
public class StudentEntity implements Serializable{
    private static final long serialVersionUID = -1428832521043330114L;
    private int id;
    private String stu_num;
    private String stu_name;
    private String stu_birthplace;
    private Date stu_birthday;
    private int stu_sex;
    private int stu_position;
    private String stu_origin_school;
    private String stu_class_num;
    private Date stu_entr_year;
    private long stu_tel;
    private String stu_id_card;
    private String stu_picture;
    private ClassItemEntity classItem_id;
    private Set<SelectedCourseEntity> SelectedCourse_id;

    public StudentEntity() {

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

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_birthplace() {
        return stu_birthplace;
    }

    public void setStu_birthplace(String stu_birthplace) {
        this.stu_birthplace = stu_birthplace;
    }

    public Date getStu_birthday() {
        return stu_birthday;
    }

    public void setStu_birthday(Date stu_birthday) {
        this.stu_birthday = stu_birthday;
    }

    public int getStu_sex() {
        return stu_sex;
    }

    public void setStu_sex(int stu_sex) {
        this.stu_sex = stu_sex;
    }

    public int getStu_position() {
        return stu_position;
    }

    public void setStu_position(int stu_position) {
        this.stu_position = stu_position;
    }

    public String getStu_origin_school() {
        return stu_origin_school;
    }

    public void setStu_origin_school(String stu_origin_school) {
        this.stu_origin_school = stu_origin_school;
    }

    public String getStu_class_num() {
        return stu_class_num;
    }

    public void setStu_class_num(String stu_class_num) {
        this.stu_class_num = stu_class_num;
    }

    public Date getStu_entr_year() {
        return stu_entr_year;
    }

    public void setStu_entr_year(Date stu_entr_year) {
        this.stu_entr_year = stu_entr_year;
    }

    public long getStu_tel() {
        return stu_tel;
    }

    public void setStu_tel(long stu_tel) {
        this.stu_tel = stu_tel;
    }

    public String getStu_id_card() {
        return stu_id_card;
    }

    public void setStu_id_card(String stu_id_card) {
        this.stu_id_card = stu_id_card;
    }

    public String getStu_picture() {
        return stu_picture;
    }

    public void setStu_picture(String stu_picture) {
        this.stu_picture = stu_picture;
    }

    public ClassItemEntity getClassItem_id() {
        return classItem_id;
    }

    public void setClassItem_id(ClassItemEntity classItem_id) {
        this.classItem_id = classItem_id;
    }

    public Set<SelectedCourseEntity> getSelectedCourse_id() {
        return SelectedCourse_id;
    }

    public void setSelectedCourse_id(Set<SelectedCourseEntity> selectedCourse_id) {
        SelectedCourse_id = selectedCourse_id;
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "id=" + id +
                ", stu_num='" + stu_num + '\'' +
                ", stu_name='" + stu_name + '\'' +
                ", stu_birthplace='" + stu_birthplace + '\'' +
                ", stu_birthday=" + stu_birthday +
                ", stu_sex=" + stu_sex +
                ", stu_position=" + stu_position +
                ", stu_origin_school='" + stu_origin_school + '\'' +
                ", stu_class_num=" + stu_class_num +
                ", stu_entr_year=" + stu_entr_year +
                ", stu_tel=" + stu_tel +
                ", stu_id_card='" + stu_id_card + '\'' +
                ", stu_picture='" + stu_picture + '\'' +
                ", classItem_id=" + classItem_id +
                ", SelectedCourse_id=" + SelectedCourse_id +
                '}';
    }
}
