package vip.designpattern.decorator.decorator;

import vip.designpattern.decorator.INaviBar;

/** 题库导航栏装饰器*/
public class QueryNavBarDecorator extends AbsNavBarDecorator {

    public QueryNavBarDecorator(INaviBar iNavigationBar) {
        super(iNavigationBar);
    }

    @Override
    public String showNavBars() {
        return super.iNavigationBar.showNavBars() + "--题库";
    }
}
