package vip.designpattern.decorator.roles.impl;

import vip.designpattern.decorator.BaseNavBar;
import vip.designpattern.decorator.decorator.QueryNavBarDecorator;
import vip.designpattern.decorator.roles.IPermission;

/** 普通会员权限*/
public class Normal implements IPermission {
    @Override
    public String showPermNavBar() {
        return new QueryNavBarDecorator(new BaseNavBar()).showNavBars();
    }
}
