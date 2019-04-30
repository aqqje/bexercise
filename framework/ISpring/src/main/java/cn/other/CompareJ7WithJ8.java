package cn.other;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CompareJ7WithJ8 {
    private static final long loopCut = 1000 * 1000 * 1000;

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println("java.version"+System.getProperty("java.version"));
        t1();
        t2();
        t3();

       /* Class<Person> c = Person.class;
        Person person = c.newInstance();
        Method m = c.getMethod("setAge", Integer.class);
        Object invoke = m.invoke(person, 32);
        System.out.println(invoke);*/
    }

    // 每次重新生成对象
    public static void t1() {
        long s = System.currentTimeMillis();
        for(int i = 0; i < loopCut; i++){
            Person p = new Person();
            p.setAge(32);
        }
        long e = System.currentTimeMillis();
        System.out.println("循环10亿次创建对象时间:"+ (e - s));

    }

    // 生成一个对象
    public static void t2() {
        long s = System.currentTimeMillis();
        Person p = new Person();
        for(int i = 0; i < loopCut; i++){
            p.setAge(32);
        }
        long e = System.currentTimeMillis();
        System.out.println("循环10亿次赋值时间:"+ (e - s));
    }

    // 反射生成对象
    public static void t3() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        long s = System.currentTimeMillis();
        Class<Person> c = Person.class;
        Person person = c.newInstance();
        Method m = c.getMethod("setAge", Integer.class);
        for(int i = 0; i < loopCut; i++){
            m.invoke(person, 32);
        }
        long e = System.currentTimeMillis();
        System.out.println("循环10亿次反射生成对象时间:"+ (e - s));
    }

static class Person{
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }
}

}
