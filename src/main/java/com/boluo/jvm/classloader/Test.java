package com.boluo.jvm.classloader;

/**
 *
 * -XX:+TraceClassLoading 用于追踪类的加载信息并打印出来
 * -XX:+<option> 开启option选项
 * -XX:-<option> 关闭option选项
 * -XX:<option>=<value> 将option选项的值设置为value
 */
public class Test {

    public static void main(String[] args) {

//        new Child();
        System.out.println(Child.str);
    }
}
class Parent {

    public static String str = "你好 菠萝 Parent";

    static {
        System.out.println("Parent static block");
    }
}

class Child extends Parent {

    public static String str2 = "你好 菠萝 Child";
    static {
        System.out.println("Child static block");
    }
}
