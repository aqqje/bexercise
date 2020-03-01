package pub.methodreference;

import pub.java8NewFeatures.methodreference.Person;
import pub.java8NewFeatures.methodreference.PersonFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by aqqje on 2020/3/1.
 */
public class MethodReferenceTest {

    public static void main(String[] args) {
        // 构造方法引用
        PersonFactory person = new PersonFactory(Person::new);
        Person p1 = person.getPerson();
        Person p2 = person.getPerson();
        Person p3 = person.getPerson();
        Person p4 = person.getPerson();
        System.out.println(person);

        List<Person> list = new ArrayList<>();
        p1.setName("1111");
        list.add(p1);
        p1.setName("2222");
        list.add(p2);
        p1.setName("3333");
        list.add(p3);
        p1.setName("4444");
        list.add(p4);

        // 静态方法引用
        list.sort(MethodReferenceTest::defineCompare);
        // 对象的实例方法引用
        list.sort(p1::compareTo);
        // 特定类型方法引用
        list.sort(Person::compareTo);

    }

    public static int defineCompare(Person p1, Person p2){
        return p1.getName().compareTo(p2.getName());
    }
}
