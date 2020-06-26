package com.boluo.jvm.classloader;

import java.io.*;

public class BoluoClassLoader extends ClassLoader {

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
