package com.boluo.jvm.classloader;

import java.io.*;
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
//        test3();
//        test4();
//        test5();
        test6();
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

    private static void test4() {

        // Class objects for array classes are not created by class loaders, but are created automatically as required by the Java runtime.
        // The class loader for an array class, as returned by Class.getClassLoader() is the same as the class loader for its element type;
        // if the element type is a primitive type, then the array class has no class loader.

        // 启动类加载器
        String[] strings = new String[1];
        System.out.println(strings.getClass().getClassLoader());

        Test10[] test10s = new Test10[1];
        System.out.println(test10s.getClass().getClassLoader());

        // 没有类加载器
        int[] ints = new int[1];
        System.out.println(ints.getClass().getClassLoader());
    }

    private static void test5() {

        // 类加载器 不同的命名空间可以加载同一个类各一次
        // 命名空间：从自定义加载器到根加载器叫一个命名空间
        BoluoClassLoader boluoClassLoader = new BoluoClassLoader("boluoClassLoader");
        boluoClassLoaderTest(boluoClassLoader);
        System.out.println("---------------------------------------------------------------------------");
        BoluoClassLoader boluoClassLoader1 = new BoluoClassLoader(boluoClassLoader, "boluoClassLoader");
        boluoClassLoaderTest(boluoClassLoader1);
        System.out.println("---------------------------------------------------------------------------");
        BoluoClassLoader boluoClassLoader2 = new BoluoClassLoader(boluoClassLoader1, "boluoClassLoader");
        boluoClassLoaderTest(boluoClassLoader2);

        System.out.println("---------------------------------------------------------------------------");
        BoluoClassLoader boluoClassLoader3 = new BoluoClassLoader("boluoClassLoader");
        boluoClassLoaderTest(boluoClassLoader3);
    }

    private static void boluoClassLoaderTest(BoluoClassLoader boluoClassLoader) {
        try {

            boluoClassLoader.setPath("C:\\Users\\111\\Desktop\\");
            Class<?> clazz = boluoClassLoader.loadClass("com.boluo.jvm.classloader.Test");
            System.out.println("clazz=" + clazz.hashCode());
            Object newInstance = clazz.newInstance();
            System.out.println(newInstance);
            System.out.println(newInstance.getClass().getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void test6() {

        // 类加载器 卸载
        // -XX:+TraceClassUnloading
        // jvisualvm
        BoluoClassLoader boluoClassLoader = new BoluoClassLoader("boluoClassLoader");
        boluoClassLoaderTest(boluoClassLoader);
        boluoClassLoader = null;
        System.gc();
        try {
            Thread.sleep(200 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------------------------------------------------------------");
        boluoClassLoader = new BoluoClassLoader("boluoClassLoader");
        boluoClassLoaderTest(boluoClassLoader);
    }
}

class BoluoClassLoader extends ClassLoader {

    private String classLoaderName;

    private String path;

    private String fileExtension = ".class";

    public BoluoClassLoader(String classLoaderName) {

        // 默认父类加载器是系统类加载器
        super();
        this.classLoaderName = classLoaderName;
    }

    public BoluoClassLoader(ClassLoader parent, String classLoaderName) {
        // 显示指定父类加载器
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {

        System.out.println("BoluoClassLoader findClass className=" + className);

        byte[] data = this.loadClassData(className);
        return this.defineClass(className, data, 0, data.length);
    }

    private byte[] loadClassData(String className) {

        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream outputStream = null;

        className = className.replace(".", "\\");

        try {

            this.classLoaderName = this.classLoaderName.replace(".", "/");
            is = new FileInputStream(new File(this.path + className + this.fileExtension));
            outputStream = new ByteArrayOutputStream();
            int ch = 0;
            while (-1 != (ch = is.read())) {
                outputStream.write(ch);
            }
            data = outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(is != null) {
                    is.close();
                }
                if(outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }
}
