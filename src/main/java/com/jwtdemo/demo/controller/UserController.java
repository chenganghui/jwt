package com.jwtdemo.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.jwtdemo.demo.entity.User;
import com.jwtdemo.demo.entity.constant.RespBean;
import com.jwtdemo.demo.service.UserService;
import com.jwtdemo.demo.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/***
 *  用户注册登录接口
 */
@RestController
@RequestMapping("/api/system")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public RespBean register(@RequestBody JSONObject body){
        User user = new User();
        user.setLoginName(body.getString("loginName"))
                .setUserName(body.getString("userName"))
                .setPassword(body.getString("password"))
                .setDepartment(body.getString("depId"));
        if (userService.insert(user)==1) {
           return RespBean.ok("注册成功~");
        }
        return RespBean.error("注册失败~");
    }

    /**
     * 用户登录并生成 token
     * @param body
     * @return
     */
    @PostMapping("/login")
    public RespBean login(@RequestBody JSONObject body) {
        String loginName = body.getString("loginName");
        String password = body.getString("password");
        return  userService.login(loginName, password);
    }

    /**
     * 测试  拦截器 登陆接口用
     * @return
     */
    @GetMapping("/getUsers")
    public RespBean getUsers() {
        List<User> users = userService.getAll();
        if(users.size()==0) {
            return RespBean.error("无数据~");
        }
        return RespBean.ok("成功~",users);
    }


}
