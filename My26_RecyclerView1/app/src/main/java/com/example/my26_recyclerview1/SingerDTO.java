package com.example.my26_recyclerview1;

import java.io.Serializable;

/*1. DB에 있는 테이블을 기본으로 하여 DTO를 만든다
 *   가정 : 1. DB 에 singerDTO라는 테이블이 있다
 *         2. singerDTO라는 테이블에 name, mobile, age,
 *             resId(이미지경로)의 칼럼이 있다
 */
public class SingerDTO implements Serializable {
    String name, mobile;
    int age, resId;

    public SingerDTO(String name, String mobile, int age, int resId) {
        this.name = name;
        this.mobile = mobile;
        this.age = age;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
