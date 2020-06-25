package com.boluo.jvm.classloader;

/**
 *
 * 调用ClassLoader类的loadClass方法加载一个类，并不是对类的主动使用，不会导致类的初始化
 */
public class Test9 {

    public static void main(String[] args) throws Exception {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Class<?> clazz = loader.loadClass("com.boluo.jvm.classloader.Parent9");
        System.out.println(clazz);

        System.out.println("-------------------------------------------------------");
        clazz = Class.forName("com.boluo.jvm.classloader.Parent9");
        System.out.println(clazz);
    }

}

class Parent9 {

    static {
        System.out.println("Parent9 static block");
    }
}
