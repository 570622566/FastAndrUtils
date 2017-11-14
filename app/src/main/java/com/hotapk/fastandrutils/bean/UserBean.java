package com.hotapk.fastandrutils.bean;

import org.litepal.crud.DataSupport;

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
}
