package designpattern.operation;

import designpattern.operation.CrossBorderPayment;
import designpattern.operation.DomesticPayment;

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
