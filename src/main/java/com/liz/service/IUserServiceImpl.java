package com.liz.service;

import com.liz.dao.IUserDao;
import com.liz.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IUserServiceImpl implements IUserService{
    @Autowired
    private IUserDao userDao;


    @Override
    public List<User> list() {
        return userDao.list();
    }

    @Override
    public User get(Integer id) {
        return userDao.get(id);
    }

    @Override
    public int insert(User u) {
        return userDao.add(u);
    }

    @Override
    public int update(User u) {
        return userDao.update(u);
    }

    @Override
    public int deleteById(Integer id) {
        //测试Spring事务控制
//        int i = userDao.delete(id);
//        int j = 1/0;
//        return i;
        return 0;
    }
}
