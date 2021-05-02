package com.gupaoedu.vip.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {
    private Integer id;

    private String userName;

    private String realName;

    private String password;

    private Integer age;

    private Integer dId;

    private Dept dept;

    private List<String> hobbies;

}
