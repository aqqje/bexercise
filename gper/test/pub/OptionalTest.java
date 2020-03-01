package pub;

import pub.java8newfeatures.Optional.Person;

import java.util.Optional;

/**
 * Create by aqqje on 2020/3/1.
 */
public class OptionalTest {

    public static String getName(Person person){
        if(null == person){
            throw new RuntimeException("unkown person");
        }
        return person.getName();
    }

    // 使用 optional 代替 null 的判断
    public static String getName2(Person person){
        return Optional.ofNullable(person).map(p->p.getName()).orElse("unkown person2");
    }

    public static void main(String[] args) {
//        Person person = null;
//        String name = getName2(person);
//        System.out.println(name);

        Person p1 = null;

        // of 使用，此对象不能为空
//        Optional<Person> op1= Optional.of(p1);
        // ofNullable 使用，允许对象为空
        Optional<Person> op2 = Optional.ofNullable(p1);
        Person person = op2.get();
        System.out.println(person);

    }
}
