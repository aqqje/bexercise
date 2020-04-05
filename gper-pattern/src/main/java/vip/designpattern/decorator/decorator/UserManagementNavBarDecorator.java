package vip.designpattern.decorator.decorator;

import vip.designpattern.decorator.INaviBar;

/** 用户管理导航栏装饰*/
public class UserManagementNavBarDecorator extends AbsNavBarDecorator {

    public UserManagementNavBarDecorator(INaviBar iNavigationBar) {
        super(iNavigationBar);
    }

    @Override
    public String showNavBars() {
        return super.iNavigationBar.showNavBars()+"--用户管理";
    }
}
