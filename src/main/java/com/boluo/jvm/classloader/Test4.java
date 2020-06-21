package com.boluo.jvm.classloader;

/**
 * 类实例化时 类将初始化
 *
 * 助记符：
 * anewarray:创建一个引用类型的数组 并将引用值压入栈顶
 * newarray:创建一个原始类型的数组 并将引用值压入栈顶
 */
public class Test4 {
    public static void main(String[] args) {
//        Parent4 parent4 = new Parent4();
//        Parent4 parent5 = new Parent4();
        Parent4[] parent4s = new Parent4[1];
        Parent4[][] parent4s1 = new Parent4[1][2];

        int[] ints = new int[2];
        char[] chars = new char[4];
    }
}

class Parent4 {

    static {

        System.out.println("Parent4 static block");
    }
}