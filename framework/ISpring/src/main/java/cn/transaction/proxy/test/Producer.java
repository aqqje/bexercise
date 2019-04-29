package cn.transaction.proxy.test;

public class Producer implements IProduct{

    @Override
    public void saleProduct(float money) {
        System.out.println("销售并拿到钱:"+money);
    }

    @Override
    public void afterService(float money) {
        System.out.println("售后并拿到钱:"+money);
    }
}
