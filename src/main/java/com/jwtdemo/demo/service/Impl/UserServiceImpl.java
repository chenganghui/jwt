package com.jwtdemo.demo.service.Impl;

import com.jwtdemo.demo.entity.User;
import com.jwtdemo.demo.entity.constant.RespBean;
import com.jwtdemo.demo.mapper.UserMapper;
import com.jwtdemo.demo.service.UserService;
import com.jwtdemo.demo.utils.JWTUtils;
import com.jwtdemo.demo.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Integer insert(User user) {
        user.setPassword(MD5Utils.MD5(user.getPassword()));
        return userMapper.insertSelective(user);
    }

    @Override
    public RespBean login(String loginName, String password) {

        User user = userMapper.login(loginName);
        System.out.println(user);
        if (user==null) {
           return RespBean.error("无此用户~");
        }

        String pwd = user.getPassword();
        if(MD5Utils.MD5(password).equals(pwd)){
            HashMap<String, String> map = new HashMap<>();
            map.put("username",user.getLoginName());
            map.put("depId",user.getDepartment());
            String token = JWTUtils.getToken(map);
            HashMap<String, String> data = new HashMap<>();
            data.put("token",token);
            System.out.println(user);

            data.put("name",user.getUserName());
            return RespBean.ok("登陆成功~",data);
        }
        return RespBean.error("密码错误~");
    }

    @Override
    public List<User> getAll() {
        return userMapper.selectAll();
    }

}
