package com.boluo.jvm.classloader;

/**
 *  final修饰符的变量 当前类不会初始化
 *
 *  助记符：
 *  ldc:将int float String类型的常量池中推送至栈顶
 *  bipush:将单字节（-128~127）的常量池推送至栈顶
 *  sipush:将一个短整型常量池（-32768~32767）推送至栈顶
 *  iconst_1:将int类型1推送至栈顶 (-1 到 5) iconst_m1 iconst_0 iconst_1
 */
public class Test2 {

    public static void main(String[] args) {
        System.out.println(Parent2.str);
        System.out.println(Parent2.age);
        System.out.println(Parent2.one);

        int i = 0;
        int j = 0;

        int a = i + j;
    }
}
class Parent2 {

    public static final String str = "你好 菠萝";
    public static final int age = 18;
    public static final int one = 1;
    static {

        System.out.println("Parent static block");
    }
}
