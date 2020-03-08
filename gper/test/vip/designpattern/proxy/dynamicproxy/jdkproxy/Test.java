package vip.designpattern.proxy.dynamicproxy.jdkproxy;


import vip.designpattern.proxy.dynamicproxy.jdkproxy.IPerson;
import vip.designpattern.proxy.dynamicproxy.jdkproxy.JdkMeipo;
import vip.designpattern.proxy.dynamicproxy.jdkproxy.Zhangsan;
import vip.designpattern.proxy.dynamicproxy.jdkproxy.ZhaoLiu;

/**
 * Created by Tom.
 */
public class Test {
    public static void main(String[] args) {
        JdkMeipo jdkMeipo = new JdkMeipo();
        IPerson zhangsan = jdkMeipo.getInstance(new Zhangsan());
        zhangsan.findLove();


        IPerson zhaoliu = jdkMeipo.getInstance(new ZhaoLiu());
        zhaoliu.findLove();

    }
}
