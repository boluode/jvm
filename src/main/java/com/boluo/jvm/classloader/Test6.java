package com.boluo.jvm.classloader;

/**
 * 准备阶段的意义：设置默认值
 */
public class Test6 {

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println("counter1 = " + Singleton.counter1);
        System.out.println("counter2 = " + Singleton.counter2);
    }
}

class Singleton {

    public static int counter1;

    private static Singleton singleton = new Singleton();

    public static Singleton getInstance() {
        return singleton;
    }

    private Singleton() {

        counter1++;
        counter2++;
    }
    public static int counter2 = 0;
}