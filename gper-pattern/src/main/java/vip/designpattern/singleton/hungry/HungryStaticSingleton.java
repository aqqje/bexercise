package vip.designpattern.singleton.hungry;

/**
 *
 * Create by aqqje on 2020/2/29.
 */
public class HungryStaticSingleton {

    /**
     * 先静态后动态
     * 先上，后下
     * 先属性，后台方法
     */
    private static final HungryStaticSingleton hungryStaticSingleton;

    static {
        hungryStaticSingleton = new HungryStaticSingleton();
    }

    private HungryStaticSingleton(){};

    public static HungryStaticSingleton getInstance(){return hungryStaticSingleton;}
}
