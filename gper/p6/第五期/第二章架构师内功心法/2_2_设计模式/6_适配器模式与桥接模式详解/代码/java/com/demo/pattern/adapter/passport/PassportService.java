package com.demo.pattern.adapter.passport;

import com.demo.pattern.adapter.passport.ResultMsg;

public class PassportService {

    public ResultMsg regitst(String username, String password){
        return new ResultMsg(200, "注册成功", null);
    }

    public ResultMsg login(String username, String password){
        return null;
    }
}
