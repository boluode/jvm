package com.boluo.jvm.classloader;

/**
 * 命名空间:
 * 子加载器所加载的类可以访问到父加载器所加载的类
 * 父加载器所加载的类不能访问到子加载器所加载的类
 */
public class Test11 {

    public static void main(String[] args) throws Exception {

        BoluoClassLoader boluoClassLoader = new BoluoClassLoader("boluoClassLoader");
        boluoClassLoader.setPath("C:\\Users\\111\\Desktop\\");

        Class<?> clazz = boluoClassLoader.loadClass("com.boluo.jvm.classloader.MySimple");
        System.out.println("class:" + clazz.hashCode());

        Object object = clazz.newInstance();
    }
}
