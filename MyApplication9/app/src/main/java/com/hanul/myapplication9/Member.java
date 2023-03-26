package com.hanul.myapplication9;

import java.sql.Date;

public class Member {
    private long id;
    private String name;
    private int age;
    private String address;
    private Date createdAt;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public Date getCreatedAt(){
        return createdAt;
    }

}
