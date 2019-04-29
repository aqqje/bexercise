package cn.transaction.proxy.test;

/**
 * 生产接口
 */
public interface IProduct {

   void saleProduct(float money);

   void afterService(float money);

}
