package vip.designpattern.factorymode;

/**
 * 支付宝支付
 */
public class AliPay implements IPay {
    public void pay() {
        System.out.println("支付宝支付");
    }
}
