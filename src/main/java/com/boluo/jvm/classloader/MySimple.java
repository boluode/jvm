package com.boluo.jvm.classloader;

public class MySimple {

    public MySimple() {
        System.out.println("MySimple is loaded by:" + this.getClass().getClassLoader());
        new MyCat();

        System.out.println("from MySimple:" + MyCat.class);
    }
}
