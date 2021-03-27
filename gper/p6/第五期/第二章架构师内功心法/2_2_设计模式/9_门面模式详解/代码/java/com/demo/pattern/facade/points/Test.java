package com.demo.pattern.facade.points;

public class Test {
    public static void main(String[] args) {
        GiftInfo giftInfo = new GiftInfo("<String 5 核心原理>");
//        QualifyService qualifyService = new QualifyService();
//        PaymentService paymentService = new PaymentService();
//        ShippingService shippingService = new ShippingService();
//
//        if(qualifyService.isAvailable(giftInfo)){
//            if(paymentService.pay(giftInfo)){
//                String shippingNo =shippingService.delivery(giftInfo);
//                System.out.println("物流系统下单成功， 物流单号： " + shippingNo);
//            }
//        }

        FacadeService facadeService = new FacadeService();
        facadeService.exchange(giftInfo);
    }
}
