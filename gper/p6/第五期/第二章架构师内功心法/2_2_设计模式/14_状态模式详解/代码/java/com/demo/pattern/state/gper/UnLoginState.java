package com.demo.pattern.state.gper;

public class UnLoginState extends UserState{
    @Override
    public void favorite() {
        this.switch2Login();
        this.appContext.getState().favorite();
    }

    @Override
    public void comment(String comment) {
        this.switch2Login();
        this.appContext.getState().favorite();
    }

    private void switch2Login(){
        System.out.println("跳转到登录页");
        this.appContext.setState(AppContext.STATE_LOGIN);
    }
}
