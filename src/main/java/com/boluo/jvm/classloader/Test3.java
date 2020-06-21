package com.boluo.jvm.classloader;

import java.util.UUID;

/**
 *
 * 当一个常量在编译期间未确定，其值就不会被放在调用类的常量池中 所以变量所在类初始化
 */
public class Test3 {

    public static void main(String[] args) {
        System.out.println(Parent3.str);
    }
}

class Parent3 {

    public static final String str = UUID.randomUUID().toString();

    static {
        System.out.println("Parent3 static block");
    }
}
