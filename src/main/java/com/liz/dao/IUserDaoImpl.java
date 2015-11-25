package com.liz.dao;

import com.liz.domain.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Frank
 * Date: 15-4-29
 * Time: 下午11:28
 * To change this template use File | Settings | File Templates.
 */
public class IUserDaoImpl  extends SqlSessionDaoSupport implements IUserDao{
    @Override
    public List<User> list() {
        return this.getSqlSession().selectList("com.liz.domain.User.listAll");
    }

    @Override
    public User get(Integer id) {
        return (User)this.getSqlSession().selectOne("com.liz.domain.User.get",id);
    }

    @Override
    public int add(User u) {
        return this.getSqlSession().insert("com.liz.domain.User.create",u);
    }

    @Override
    public int update(User u) {
       return this.getSqlSession().update("com.liz.domain.User.update",u);
    }

    @Override
    public int delete(Integer id) {
        return this.getSqlSession().delete("com.liz.domain.User.delete",id);
    }
}
