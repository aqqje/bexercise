package pub.FunctionalInterface;

import pub.java8NewFeatures.functionlInterface.SayHelloService;

import java.util.function.Predicate;

/**
 * Create by aqqje on 2020/3/1.
 */
public class SayHelloTest {

    public String demo(Predicate<Integer> predicate, Integer num){
        if(predicate.test(num)){
            return "True";
        }else{
            return "False";
        }
    }

    public static void main(String[] args) {
        SayHelloService sayHelloService= (talk)->{
            System.out.println(talk);
        };
        sayHelloService.sayHalle("test");


        // Predicate

        Predicate<Integer> predicate = i -> i > 10;
        System.out.println(new SayHelloTest().demo(predicate, 12));
        // Consumer
        // Function
        // Supplier
        // BinaryOperator

    }
}
