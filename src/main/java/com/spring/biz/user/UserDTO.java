package com.spring.biz.user;

import java.sql.Date;

public class UserDTO {
    private String e_mail;
    private String password;
    private String name;
    private String address;
    private String address_detail;
    private String phone;
    private String sex;
    private int age;
    private Date regdate;

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress_detail() {
        return address_detail;
    }

    public void setAddress_detail(String address_detail) {
        this.address_detail = address_detail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    @Override
    public String toString() {
        return "User{" +
                "e_mail='" + e_mail + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", address_detail='" + address_detail + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", regdate=" + regdate +
                '}';
    }
}
