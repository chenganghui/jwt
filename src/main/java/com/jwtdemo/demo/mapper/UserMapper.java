package com.jwtdemo.demo.mapper;

import com.jwtdemo.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    Integer insertSelective(User user);

    User login(String loginName);

    List<User> selectAll();
}
