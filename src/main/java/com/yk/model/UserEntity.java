package com.yk.model;

import java.io.Serializable;

/**
 * Created by dylanyang on 10/3/15.
 */
public class UserEntity implements Serializable{
    private static final long serialVersionUID = -3735112112137412333L;

    private int id;
    private String name;
    private String passwd;
    private int age;

    public UserEntity() {

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passwd='" + passwd + '\'' +
                ", age=" + age +
                '}';
    }
}
