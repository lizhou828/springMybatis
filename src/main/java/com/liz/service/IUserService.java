package com.liz.service;

import com.liz.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Frank
 * Date: 15-4-29
 * Time: 下午11:29
 * To change this template use File | Settings | File Templates.
 */
public interface IUserService {
     public List<User> list();
     public User get(Integer id);
     public int insert(User u);
     public int update(User u);
     public int deleteById(Integer id);
}
