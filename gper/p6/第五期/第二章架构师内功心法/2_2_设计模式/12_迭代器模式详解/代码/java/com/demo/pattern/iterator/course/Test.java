package com.demo.pattern.iterator.course;

public class Test {
    public static void main(String[] args) {
        Course java = new Course("Java架构");
        Course javaBase = new Course("Java基础");
        Course design = new Course("设计模式");
        Course ai = new Course("AI人工智能");

        ICourseAggregate aggregate = new CourseAggregateImpl();
        aggregate.add(java);
        aggregate.add(javaBase);
        aggregate.add(design);
        aggregate.add(ai);

        System.out.println("=========课程列表========");

        printCourse(aggregate);
        System.out.println("=========删除之后========");
        aggregate.remove(ai);

        printCourse(aggregate);

    }

    private static void printCourse(ICourseAggregate aggregate) {
        Iterator<Course> iterator = aggregate.iterator();
        while (iterator.hasNext()){
            Course next = iterator.next();
            System.out.println(" <" + next.getName() +">");
        }
    }
}
