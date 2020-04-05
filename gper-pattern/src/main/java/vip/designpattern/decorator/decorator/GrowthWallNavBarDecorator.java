package vip.designpattern.decorator.decorator;

import vip.designpattern.decorator.INaviBar;

/** 成长墙装饰器*/
public class GrowthWallNavBarDecorator extends AbsNavBarDecorator {

    public GrowthWallNavBarDecorator(INaviBar iNavigationBar) {
        super(iNavigationBar);
    }

    @Override
    public String showNavBars() {
        return super.iNavigationBar.showNavBars() + "--成长墙";
    }
}
