package designpattern.operation;

/**
 * 跨境支付
 */
public class CrossBorderPayment extends AbstractPayFactory{

    public IPay CreateApplePay(){ super.init(); return new ApplePay();}
}
