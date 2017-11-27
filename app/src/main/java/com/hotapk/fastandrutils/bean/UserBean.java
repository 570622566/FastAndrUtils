package com.hotapk.fastandrutils.bean;

import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 * @author laijian
 * @version 2017/11/10
 * @Copyright (C)下午5:27 , www.hotapk.cn
 */
public class UserBean extends DataSupport {

    private String username = "";
    private String passw = "";
    private int age;
    private String phone = "";
    private double aDouble = 0.1d;
    private float aFloat = 2f;
    private boolean aBoolean = true;
    private byte[] abytes;
    private Date date;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getaDouble() {
        return aDouble;
    }

    public void setaDouble(double aDouble) {
        this.aDouble = aDouble;
    }

    public float getaFloat() {
        return aFloat;
    }

    public void setaFloat(float aFloat) {
        this.aFloat = aFloat;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public byte[] getAbytes() {
        return abytes;
    }

    public void setAbytes(byte[] abytes) {
        this.abytes = abytes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
