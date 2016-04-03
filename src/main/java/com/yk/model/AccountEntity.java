package com.yk.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dylanyang on 10/11/15.
 */
public class AccountEntity implements Serializable{

    private static final long serialVersionUID = -6243952634922063446L;
    private int id;
    private String username;
    private String password;
    private String name_ch;
    private Date create_time;
    private String email;
    private int role;
    private String id_card;
    private int dept;
    private TeacherEntity teacher_id;

    public AccountEntity() {

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public AccountEntity(String username,String password){
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName_ch() {
        return name_ch;
    }

    public void setName_ch(String name_ch) {
        this.name_ch = name_ch;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
    }

    public TeacherEntity getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(TeacherEntity teacher_id) {
        this.teacher_id = teacher_id;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name_ch='" + name_ch + '\'' +
                ", create_time=" + create_time +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", id_card='" + id_card + '\'' +
                ", dept=" + dept +
                ", teacher_id=" + teacher_id +
                '}';
    }
}
