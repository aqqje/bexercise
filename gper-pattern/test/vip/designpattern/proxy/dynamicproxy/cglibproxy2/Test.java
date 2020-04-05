package vip.designpattern.proxy.dynamicproxy.cglibproxy2;

public class Test {
    public static void main(String[] args) {
        CglibMeipo2 cglibMeipo2 = new CglibMeipo2();
        Customer customer = (Customer)cglibMeipo2.getInstance(Customer.class);
        customer.findLove();
    }
}
