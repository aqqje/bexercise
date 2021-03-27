package com.demo.pattern.adapter.passport.adapterv1;

import com.demo.pattern.adapter.passport.ResultMsg;
import com.demo.pattern.adapter.passport.PassportService;

public class PassportForThirdAdapter extends PassportService implements IPassportForThird {
    @Override
    public ResultMsg loginForQQ(String openId) {
        return loginForRegist(openId, null);
    }

    @Override
    public ResultMsg loginForWechar(String openId) {
        return loginForRegist(openId, null);
    }

    @Override
    public ResultMsg loginForToken(String token) {
        return loginForRegist(token, null);
    }

    @Override
    public ResultMsg loginForTelphone(String phone, String code) {
        return loginForRegist(phone, null);
    }

    private ResultMsg loginForRegist(String username, String password){
        if(null == password){
            password = "THIRD_EMPTY";
        }
        super.regitst(username, password);
        return super.login(username, password);
    }
}
