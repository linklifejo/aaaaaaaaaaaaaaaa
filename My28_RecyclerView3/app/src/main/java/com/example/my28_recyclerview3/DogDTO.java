package com.example.my28_recyclerview3;

import java.io.Serializable;
public class DogDTO implements Serializable {
    String dog,sex, addr;
    int age,resId;
    public DogDTO(String dog, String sex, String addr, int age, int resId) {
        this.dog = dog;
        this.sex = sex;
        this.addr = addr;
        this.age = age;
        this.resId = resId;
    }
    public String getDog() {
        return dog;
    }

    public void setDog(String dog) {
        this.dog = dog;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }


}
