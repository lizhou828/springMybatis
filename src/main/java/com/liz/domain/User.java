package com.liz.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Frank
 * Date: 15-4-29
 * Time: 下午11:27
 * To change this template use File | Settings | File Templates.
 */

public class User implements Serializable{

    private static final long serialVersionUID = 7267885230456379454L;

    private Integer id;
    private String name;
    private String email;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User() {
    }

    public User(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
