package com.boluo.jvm.bytecode;

public class CodeTest2 {

    String str = "菠萝";
    private int x = 5;
    public static Integer in = 10;
//    public final static Integer fin = 10086;

    public static void main(String[] args) {

        CodeTest2 codeTest2 = new CodeTest2();
        codeTest2.setX(8);

        in = 20;
    }

    private synchronized void setX(int x) {
        this.x = x;
    }

    private void test(String str) {

        synchronized (str) {
            System.out.println("test run...");
        }
    }

    private static synchronized void test2() {

    }
}
