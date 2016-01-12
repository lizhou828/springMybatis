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

    private Long  userId;
    private String userName;
    private String email;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public User(Long userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }
}
