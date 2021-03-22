package com.demo.pattern.singleton.Seriable.test;

import com.demo.pattern.singleton.Seriable.SeriableSingleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化破坏单例测试
 */
public class SeriableSingletonTest {

    public static void main(String[] args) {

        SeriableSingleton s1 = SeriableSingleton.getInstance();
        SeriableSingleton s2 = null;

        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream("SeriableSingleton.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s1);
            oos.flush();
            oos.close();

            FileInputStream fis = new FileInputStream("SeriableSingleton.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            s2 = (SeriableSingleton) ois.readObject();
            ois.close();

            System.out.println(s1);
            System.out.println(s2);
            System.out.println(s1 == s2);

        } catch (Exception e) {

        }
    }
}
