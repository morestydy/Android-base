package com.example.helloworld.denglu.db.domain;

/**
 * com.example.helloworld.denglu.db.domain
 * HelloWorld
 *
 * @author:Tom 2020/8/20
 * 描述:
 **/

public class User {
    private int id;
    private String name;
    private String psw;

    public User() {
    }

    public User(String name, String psw) {
        this.name = name;
        this.psw = psw;
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

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
}
