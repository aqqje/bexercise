package vip.designpattern.decorator.decorator;

import vip.designpattern.decorator.INaviBar;

/** 抽象装饰器，用于扩展实例*/
public abstract class AbsNavBarDecorator implements INaviBar {

    public INaviBar iNavigationBar;

    public AbsNavBarDecorator(INaviBar iNavigationBar){
        this.iNavigationBar = iNavigationBar;
    }

    @Override
    public abstract String showNavBars();
}
