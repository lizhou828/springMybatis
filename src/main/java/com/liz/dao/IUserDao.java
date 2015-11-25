package com.liz.dao;

import com.liz.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Frank
 * Date: 15-4-29
 * Time: 下午11:28
 * To change this template use File | Settings | File Templates.
 */
public interface IUserDao {
    public List<User> list();
    public User get(Integer id);
    public int add(User u);
    public int update(User u);
    public int delete(Integer id);
}
