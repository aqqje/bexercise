package vip.designpattern.factorymode;

/**
 * 测试
 */
public class PaymentTest {
    public static void main(String[] args) {
        CrossBorderPayment crossBorderPayment = new CrossBorderPayment();
        crossBorderPayment.CreateApplePay().pay();

        DomesticPayment domesticPayment = new DomesticPayment();
        domesticPayment.createAliPay().pay();
        domesticPayment.createUniPay().pay();
        domesticPayment.createWxPay().pay();
    }
}
