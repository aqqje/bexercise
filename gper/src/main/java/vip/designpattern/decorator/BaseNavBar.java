package vip.designpattern.decorator;

/** 基础导航栏 */
public class BaseNavBar implements INaviBar {

    @Override
    public String showNavBars() {
        return "问答--文章--精品课--冒泡--商城";
    }
}
