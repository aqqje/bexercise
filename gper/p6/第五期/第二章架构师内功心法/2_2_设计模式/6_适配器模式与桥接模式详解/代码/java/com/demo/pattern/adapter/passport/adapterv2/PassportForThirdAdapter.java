package com.demo.pattern.adapter.passport.adapterv2;

import com.demo.pattern.adapter.passport.ResultMsg;
import com.demo.pattern.adapter.passport.adapterv2.adapters.ILoginAdapter;
import com.demo.pattern.adapter.passport.adapterv2.adapters.LoginForQQAdapter;

public class PassportForThirdAdapter implements IPassportForThird {
    @Override
    public ResultMsg loginForQQ(String openId) {
        return processLogin(openId, LoginForQQAdapter.class);
    }

    @Override
    public ResultMsg loginForWechar(String openId) {
        return processLogin(openId, LoginForQQAdapter.class);
    }

    @Override
    public ResultMsg loginForToken(String token) {
        return processLogin(token, LoginForQQAdapter.class);
    }

    @Override
    public ResultMsg loginForTelphone(String phone, String code) {
        return processLogin(phone, LoginForQQAdapter.class);
    }

    private ResultMsg processLogin(String id, Class<? extends ILoginAdapter> clazz){
        try {
            ILoginAdapter adapter = clazz.newInstance();
            if(adapter.support(adapter)){
                return adapter.login(id, adapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
