package com.boluo.jvm.classloader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 *
 * 系统类加载器----扩展类加载器----根加载器
 */
public class Test10 {

    public static void main(String[] args) {

//        test1();
//        test2();
        test3();
    }

    private static void test1() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        System.out.println(classLoader);

        while (classLoader != null) {

            classLoader = classLoader.getParent();
            System.out.println(classLoader);
        }
    }

    private static void test2() {

        try {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

            String resourceName = "com/boluo/jvm/classloader/Test10.class";
            Enumeration<URL> resources = contextClassLoader.getResources(resourceName);

            while (resources.hasMoreElements()) {

                URL url = resources.nextElement();
                System.out.println(url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test3() {

        Class<?> clazz = Test10.class;
        System.out.println(clazz.getClassLoader());

        clazz = String.class;
        System.out.println(clazz.getClassLoader());
    }
}
