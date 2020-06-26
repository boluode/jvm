package com.boluo.jvm.classloader;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 *
 * 当前类加载器 Current Classloader
 *
 * 每个类都会使用自己的类加载器（即加载自身的类加载器）来去加载其他类
 * 例如：ClassA引用ClassB 那么ClassA的类加载器就会去加载ClassB
 *
 * 线程上下文类加载器 Context Classloader
 * 作用：
 * 父Classloader可以使用当前线程Thread.currentThread().getContextClassLoader()所指定的classloader加载的类。这就改变了父classloader
 * 不能使用子classloader或是其他没有直接父子关系的classloader加载的类的情况，即改变了双亲委派模型
 *
 * SPI Service Provider Interface
 *
 * 线程上下文类加载器的一般使用模式     获取 - 使用 - 还原
 * ClassLoader classLoader = Thread.currentThread().getContextClassLoader()
 * try {
 *     Thread.currentThread().setContextClassLoader(targetClassloader);
 *     ...
 * } finally {
 *     Thread.currentThread().setContextClassLoader(classLoader);
 * }
 */
public class Test13 {

    public static void main(String[] args) throws Exception {

        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(Thread.class.getClassLoader());

        System.out.println("-----------------------------------------------------");
        Thread13_1 thread13_1 = new Thread13_1();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------------------------------");

        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);
        Iterator<Driver> driverIterator = serviceLoader.iterator();
        while (driverIterator.hasNext()) {
            Driver driver = driverIterator.next();
            System.out.println("driver:" + driver.getClass() + ",loader:" + driver.getClass().getClassLoader());
        }
        System.out.println("当前线程上下文类加载器:" + Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader的类加载器:" + ServiceLoader.class.getClassLoader());
        System.out.println("-----------------------------------------------------");
        Class.forName("com.mysql.jdbc.Driver");
        DriverManager.getConnection("", "", "");
    }
}

class Thread13_1 implements Runnable {

    private Thread thread;

    public Thread13_1() {

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        ClassLoader classLoader = this.thread.getContextClassLoader();

        this.thread.setContextClassLoader(classLoader);

        System.out.println("Class:" + classLoader.getClass());
        System.out.println("Parent:" + classLoader.getParent().getClass());
    }
}
