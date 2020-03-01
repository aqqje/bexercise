package pub.lambda;

/**
 * Create by aqqje on 2020/3/1.
 */
public class LambdaTest {

    public static void main(String[] args) {

        // lambda 之前
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("run...");
            }
        });
        thread.start();

        // lambda 之后
        Thread aqqje = new Thread(() -> System.out.println("aqqje"));
        aqqje.start();
    }
}
