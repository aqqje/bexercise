package com.demo.pattern.state.gper;

public abstract class UserState {

    protected AppContext appContext;

    public void setContext(AppContext context){
        this.appContext = context;
    }

    public abstract void favorite();

    public abstract void comment(String comment);


}
