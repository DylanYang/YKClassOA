package com.yk.model;

import java.io.Serializable;

/**
 * Created by dylanyang on 10/11/15.
 */
public class StuFamilyEntity implements Serializable{
    private static final long serialVersionUID = 8205493417537929966L;

    private int id;
    private long stu_id;
    private String father_name;
    private long father_tel;
    private String father_id_card;
    private String mother_name;
    private long mother_tel;
    private String mother_id_card;
    private String family_addr;

    public StuFamilyEntity() {

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

    public long getStu_id() {
        return stu_id;
    }

    public void setStu_id(long stu_id) {
        this.stu_id = stu_id;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public long getFather_tel() {
        return father_tel;
    }

    public void setFather_tel(long father_tel) {
        this.father_tel = father_tel;
    }

    public String getFather_id_card() {
        return father_id_card;
    }

    public void setFather_id_card(String father_id_card) {
        this.father_id_card = father_id_card;
    }

    public String getMother_name() {
        return mother_name;
    }

    public void setMother_name(String mother_name) {
        this.mother_name = mother_name;
    }

    public long getMother_tel() {
        return mother_tel;
    }

    public void setMother_tel(long mother_tel) {
        this.mother_tel = mother_tel;
    }

    public String getMother_id_card() {
        return mother_id_card;
    }

    public void setMother_id_card(String mother_id_card) {
        this.mother_id_card = mother_id_card;
    }

    public String getFamily_addr() {
        return family_addr;
    }

    public void setFamily_addr(String family_addr) {
        this.family_addr = family_addr;
    }

    @Override
    public String toString() {
        return "StuFamilyEntity{" +
                "id=" + id +
                ", stu_id=" + stu_id +
                ", father_name='" + father_name + '\'' +
                ", father_tel=" + father_tel +
                ", father_id_card='" + father_id_card + '\'' +
                ", mother_name='" + mother_name + '\'' +
                ", mother_tel=" + mother_tel +
                ", mother_id_card='" + mother_id_card + '\'' +
                ", family_addr='" + family_addr + '\'' +
                '}';
    }
}
