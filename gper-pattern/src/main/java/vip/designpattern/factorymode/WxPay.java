package vip.designpattern.factorymode;

/**
 * 微信支付
 */
public class WxPay implements IPay {
    public void pay() {
        System.out.println("微信支付");
    }
}
