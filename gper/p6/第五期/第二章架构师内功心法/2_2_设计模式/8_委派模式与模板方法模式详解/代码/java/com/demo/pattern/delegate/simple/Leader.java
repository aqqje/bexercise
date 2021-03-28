package com.demo.pattern.delegate.simple;

import java.util.HashMap;
import java.util.Map;

public class Leader implements IEmployee {

    private Map<String, IEmployee> employees = new HashMap<>();

    public Leader(){
        employees.put("编程", new EmployeeA());
        employees.put("平面设计", new EmployeeB());
    }

    @Override
    public void doing(String task) {
//        if("编程".equals(task)){
//            new EmployeeA().doing(task);
//        } else if ("平面设计".equals(task)) {
//            new EmployeeB().doing(task);
//        } else {
//            System.out.println("这个任务" + task + "超出我的能力范围");
//        }

        if(!employees.containsKey(task)){
            System.out.println("这个任务" + task + "超出我的能力范围");
        }
        employees.get(task).doing(task);
    }
}
