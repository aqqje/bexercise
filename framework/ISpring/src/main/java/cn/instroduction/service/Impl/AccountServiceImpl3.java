package cn.instroduction.service.Impl;

import cn.instroduction.service.IAccountService;

import java.util.Date;

public class AccountServiceImpl3 implements IAccountService {

    private String username;
    private int age;
    private Date birthday;

    public AccountServiceImpl3() {
    }

    public AccountServiceImpl3(String username, int age, Date birthday) {
        this.username = username;
        this.age = age;
        this.birthday = birthday;
    }

    @Override
    public void save() {
        System.out.println(username);
        System.out.println(birthday);
        System.out.println(age);
    }

    @Override
    public String toString() {
        return "AccountServiceImpl1{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
