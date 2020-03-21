package vip.designpattern.decorator.roles.impl;

import vip.designpattern.decorator.BaseNavBar;
import vip.designpattern.decorator.decorator.GrowthWallNavBarDecorator;
import vip.designpattern.decorator.decorator.QueryNavBarDecorator;
import vip.designpattern.decorator.decorator.UserManagementNavBarDecorator;
import vip.designpattern.decorator.roles.IPermission;

/** 管理者权限*/
public class Admin implements IPermission {

    @Override
    public String showPermNavBar() {
        return new UserManagementNavBarDecorator(new QueryNavBarDecorator(new GrowthWallNavBarDecorator(new BaseNavBar()))).showNavBars();
    }
}
