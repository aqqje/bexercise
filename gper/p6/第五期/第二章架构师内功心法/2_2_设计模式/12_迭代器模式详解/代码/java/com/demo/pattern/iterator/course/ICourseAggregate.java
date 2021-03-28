package com.demo.pattern.iterator.course;

import java.sql.Connection;

public interface ICourseAggregate {

    void add(Course course);
    void remove(Course course);

    Iterator<Course> iterator();
}
