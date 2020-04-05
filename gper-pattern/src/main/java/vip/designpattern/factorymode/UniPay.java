package vip.designpattern.factorymode;

/**
 * 银联支付
 */
public class UniPay implements IPay {
    public void pay() {
        System.out.println("银联支付");
    }
}
