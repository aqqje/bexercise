package com.demo.pattern.adapter.passport.adapterv2;

import com.demo.pattern.adapter.passport.ResultMsg;

public interface IPassportForThird {

    ResultMsg loginForQQ(String opentId);

    ResultMsg loginForWechar(String openId);

    ResultMsg loginForToken(String token);

    ResultMsg loginForTelphone(String phone, String code);
}
