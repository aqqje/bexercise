package com.aqqje.spi;

import java.util.ServiceLoader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ServiceLoader<DataBaseDriver> dataBaseDrivers = ServiceLoader.load(DataBaseDriver.class);
        for (DataBaseDriver dataBaseDriver : dataBaseDrivers) {
            System.out.println(dataBaseDriver.buildConnect("localhost"));
        }
    }
}
