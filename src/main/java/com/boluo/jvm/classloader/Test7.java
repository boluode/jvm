package com.boluo.jvm.classloader;

public class Test7 {

    public static void main(String[] args) {

        System.out.println(Child7.b);
    }

}
interface Parent7 {

    Thread thread = new Thread() {
        {
            System.out.println("父接口初始化");
        }
    };
}

class Child7 implements Parent7 {

    public static int b = 9;
}
