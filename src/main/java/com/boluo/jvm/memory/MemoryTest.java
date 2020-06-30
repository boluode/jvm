package com.boluo.jvm.memory;

import java.util.ArrayList;
import java.util.List;

public class MemoryTest {

    public static void main(String[] args) {

        List<MemoryTest> memoryTestList = new ArrayList<>();
        for ( ; ;) {
            memoryTestList.add(new MemoryTest());

//            System.gc();
        }
    }
}
