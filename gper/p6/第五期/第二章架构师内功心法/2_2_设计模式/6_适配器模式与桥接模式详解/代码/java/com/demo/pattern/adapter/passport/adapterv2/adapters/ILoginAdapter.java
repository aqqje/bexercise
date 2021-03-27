package com.demo.pattern.adapter.passport.adapterv2.adapters;

import com.demo.pattern.adapter.passport.ResultMsg;

public interface ILoginAdapter {
    boolean support(Object object);
    ResultMsg login(String id, Object adapter);
}
