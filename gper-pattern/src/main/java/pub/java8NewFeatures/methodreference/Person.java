package pub.java8NewFeatures.methodreference;

/**
 * Create by aqqje on 2020/3/1.
 */
public class Person implements Comparable<Person> {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int compareTo(Person p1, Person p2){
        return p1.getName().compareTo(p2.getName());
    }

    @Override
    public int compareTo(Person o) {
        return o.getName().compareTo(this.getName());
    }
}
