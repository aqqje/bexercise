package cn.aqqje.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private int id;
    private String name;
    private String gender;
    private int age;
    private Date brithday;

    public User() {
    }

    public User(int id, String name, String gender, int age, Date brithday) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.brithday = brithday;
    }

    public String getBrithStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年mm月dd日 HH:MM:ss");
        String dateStr = sdf.format(brithday);
        return dateStr;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
