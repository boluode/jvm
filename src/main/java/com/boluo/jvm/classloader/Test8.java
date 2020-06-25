package com.boluo.jvm.classloader;

public class Test8 {

    public static void main(String[] args) throws Exception {

        Class<?> clazz = Class.forName("java.lang.String");
        System.out.println(clazz.getClassLoader());

        Class<?> parent8Clazz = Class.forName("com.boluo.jvm.classloader.Parent8");
        System.out.println(parent8Clazz.getClassLoader());

        System.out.println(Parent8.a);
    }
}

class Parent8 {

    public static final int a = 8;

    static {

        System.out.println("Parent8 static block");
    }
}

class Child8 {
    public static final int b = 8;

    static {

        System.out.println("Child8 static block");
    }
}
