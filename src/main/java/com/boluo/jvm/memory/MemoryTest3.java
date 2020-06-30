package com.boluo.jvm.memory;

/**
 * 堆溢出
 */
public class MemoryTest3 {

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            C1.staticMethod();
        });
        Thread thread2 = new Thread(() -> {
            C3.staticMethod();
        });
        thread1.start();
        thread2.start();
    }
}

class C1 {

    public static synchronized void staticMethod() {

        System.out.println("C1 staticMethod enter");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        C3.staticMethod();
    }
}

class C3 {

    public static synchronized void staticMethod() {

        System.out.println("C3 staticMethod enter");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        C1.staticMethod();
    }
}
