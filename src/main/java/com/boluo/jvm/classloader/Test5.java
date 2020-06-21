package com.boluo.jvm.classloader;

/**
 * 接口默认参数 public static final
 *
 */
public class Test5 {

    public static void main(String[] args) {
        System.out.println(Child5.b);
    }
}

interface Parent5 {
    public static final int a = 5;
}

interface Child5 extends Parent5 {
    int b = 6;
}
