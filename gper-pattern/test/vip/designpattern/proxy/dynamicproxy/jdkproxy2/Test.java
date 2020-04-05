package vip.designpattern.proxy.dynamicproxy.jdkproxy2;

public class Test {
    public static void main(String[] args) {
        // 实例一个代理对象
        JdkMeipo2 jdkMeipo2 = new JdkMeipo2();
        IPerson zhangsan = jdkMeipo2.getInstance(new Zhangsan());
        zhangsan.findLove();
        IPerson zhaoLiu = jdkMeipo2.getInstance(new ZhaoLiu());
        zhaoLiu.findLove();
    }
}
