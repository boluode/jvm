package com.boluo.jvm.classloader;

import sun.misc.Launcher;

import java.lang.reflect.Method;

/**
 *
 *  双亲委派模型：
 *  1、可以确保Java核心库的类型安全
 *  2、确保Java核心类库所提供的类不会被自定义的类所替代
 *  3、不同的类加载器可以为相同名称的类创建额外的命名空间
 */
public class Test12 {

    public static void main(String[] args) throws Exception {

        // 根加载器
        System.out.println(System.getProperty("sun.boot.class.path"));
        // 扩展类加载器
        System.out.println(System.getProperty("java.ext.dirs"));
        // 系统类加载器
        System.out.println(System.getProperty("java.class.path"));

        BoluoClassLoader boluoClassLoader = new BoluoClassLoader("boluoClassLoader");
        boluoClassLoader.setPath("C:\\Users\\111\\Desktop\\");

        Class<?> clazz = boluoClassLoader.loadClass("com.boluo.jvm.classloader.Test");
        System.out.println("class:" + clazz.hashCode());
        System.out.println("class loader:" + clazz.getClassLoader());
        System.out.println("-----------------------------------------------------------------");
        System.out.println(ClassLoader.class.getClassLoader());
        System.out.println(Launcher.class.getClassLoader());
        System.out.println(BoluoClassLoader.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());

        test();
    }

    private static void test() throws Exception {
        System.out.println("-----------------------------------------------------------------");
        BoluoClassLoader boluoClassLoader1 = new BoluoClassLoader("boluoClassLoader1");
        boluoClassLoader1.setPath("C:\\Users\\111\\Desktop\\");
        BoluoClassLoader boluoClassLoader2 = new BoluoClassLoader("boluoClassLoader2");
        boluoClassLoader2.setPath("C:\\Users\\111\\Desktop\\");

        Class<?> clazz1 = boluoClassLoader1.loadClass("com.boluo.jvm.classloader.Parent12");
        Class<?> clazz2 = boluoClassLoader2.loadClass("com.boluo.jvm.classloader.Parent12");
        System.out.println(clazz1 == clazz2);

        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();

        Method method = clazz1.getMethod("setParent12", Object.class);
        method.invoke(object1, object2);
    }
}

class Parent12 {
    private Parent12 parent12;

    public void setParent12(Object object) {

        parent12 = (Parent12) object;
    }
}
