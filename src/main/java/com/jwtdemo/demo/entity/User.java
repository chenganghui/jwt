package com.jwtdemo.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {

    private Integer id;

    private String loginName;

    private String userName;

    private String Password;

    private String department;

}
