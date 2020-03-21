package vip.designpattern.decorator.roles.impl;

import vip.designpattern.decorator.BaseNavBar;
import vip.designpattern.decorator.decorator.GrowthWallNavBarDecorator;
import vip.designpattern.decorator.decorator.QueryNavBarDecorator;
import vip.designpattern.decorator.roles.IPermission;

/** vip会员权限*/
public class Vip implements IPermission {
    @Override
    public String showPermNavBar() {
        return new GrowthWallNavBarDecorator(new QueryNavBarDecorator(new BaseNavBar())).showNavBars();
    }
}
