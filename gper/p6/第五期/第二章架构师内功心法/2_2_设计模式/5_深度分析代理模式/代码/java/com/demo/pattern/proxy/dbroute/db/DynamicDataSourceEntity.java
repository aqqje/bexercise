package com.demo.pattern.proxy.dbroute.db;

public class DynamicDataSourceEntity {
    public final static String DEFFAULT_SOURCE = null;

    private final static ThreadLocal<String> local = new ThreadLocal<String>();

    public DynamicDataSourceEntity(){}

    public static String get(){
        return local.get();
    }

    public static void restore(){
        local.set(DEFFAULT_SOURCE);
    }

    public static void set(String source){
        local.set(source);
    }

    public static void set(int year){local.set("DB_" + year);}
}
