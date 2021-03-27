package com.demo.pattern.adapter.passport.adapterv2.adapters;

import com.demo.pattern.adapter.passport.PassportService;
import com.demo.pattern.adapter.passport.ResultMsg;

public class AbstraceAdapter extends PassportService implements ILoginAdapter{
    @Override
    public boolean support(Object object) {
        return false;
    }

    @Override
    public ResultMsg login(String id, Object adapter) {
        return null;
    }

    protected ResultMsg loginForRegist(String username, String password){
        if(null == password){
            password = "THIRD_EMPTY";
        }
        super.regitst(username, password);
        return super.login(username, password);
    }
}
