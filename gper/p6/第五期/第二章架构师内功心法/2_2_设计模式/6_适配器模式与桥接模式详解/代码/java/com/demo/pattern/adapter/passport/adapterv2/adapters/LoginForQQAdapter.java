package com.demo.pattern.adapter.passport.adapterv2.adapters;

import com.demo.pattern.adapter.passport.PassportService;
import com.demo.pattern.adapter.passport.ResultMsg;

public class LoginForQQAdapter extends AbstraceAdapter {
    @Override
    public boolean support(Object adapter) {
        return adapter instanceof LoginForQQAdapter;
    }

    @Override
    public ResultMsg login(String id, Object adapter) {
        return super.loginForRegist(id, null);
    }



}
