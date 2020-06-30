package com.boluo.jvm.memory;

public class MemoryTest2 {

    private int length;

    public int getLength() {
        return length;
    }

    public void test() {

        this.length ++;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test();
    }

    public static void main(String[] args) {
        MemoryTest2 memoryTest2 = new MemoryTest2();
        try {
            memoryTest2.test();
        } catch (Throwable e) {
            System.out.println(memoryTest2.length);
            e.printStackTrace();
        }
    }
}
