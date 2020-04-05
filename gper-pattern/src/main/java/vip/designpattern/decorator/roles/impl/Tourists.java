package vip.designpattern.decorator.roles.impl;

import vip.designpattern.decorator.BaseNavBar;
import vip.designpattern.decorator.roles.IPermission;

/** 游客权限*/
public class Tourists implements IPermission {

    @Override
    public String showPermNavBar() {
        return new BaseNavBar().showNavBars();
    }
}
