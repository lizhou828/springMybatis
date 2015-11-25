package com.liz.controller;

import com.liz.common.util.JsonUtil;
import com.liz.domain.User;
import com.liz.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Frank
 * Date: 15-5-31
 * Time: P.M 10:53
 *
 * 用户类
 */

@Controller
@RequestMapping(value = "/user")
public class UserController {
    Logger logger  = Logger.getLogger(this.getClass().getName());

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/list")
    public ModelAndView list(){
        logger.info("user.list is running. log4j......");
        List<User> userList = userService.list();
       return new ModelAndView("user/list").addObject("userName","Jim").addObject("userList",userList);
    }

    @RequestMapping(value="/detail/{id}")
    @ResponseBody
    public String detail(@PathVariable Integer id){
        User user = userService.get(id);
        return JsonUtil.parse(user);
    }

}
