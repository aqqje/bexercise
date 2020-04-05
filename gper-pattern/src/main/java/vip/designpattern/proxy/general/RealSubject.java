package vip.designpattern.proxy.general;

/**
 * Created by Tom.
 */
public class RealSubject implements ISubject {

    @Override
    public void request() {
        System.out.println("real service is called.");
    }

}
