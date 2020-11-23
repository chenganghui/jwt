package com.jwtdemo.demo.service;

import com.jwtdemo.demo.entity.User;
import com.jwtdemo.demo.entity.constant.RespBean;

import java.util.List;

public interface UserService {
    Integer insert(User user);

    RespBean login(String loginName, String password);

    List<User> getAll();
}
