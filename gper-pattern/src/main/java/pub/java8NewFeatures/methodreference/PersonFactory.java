package pub.java8NewFeatures.methodreference;

import java.util.function.Supplier;

/**
 * Create by aqqje on 2020/3/1.
 */
public class PersonFactory {
    private Supplier<Person> supplier;

    public PersonFactory(Supplier<Person> supplier) {
        this.supplier = supplier;
    }

    public Person getPerson() {
        return supplier.get();
    }
}
