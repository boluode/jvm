package com.boluo.jvm.bytecode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;

public class CodeTest3 {

    public void test() {

        try {

            InputStream inputStream = new FileInputStream("boluo.txt");
            ServerSocket serverSocket = new ServerSocket(8888);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally");
        }
    }

    public void test1() throws FileNotFoundException, IOException {

        try {

            InputStream inputStream = new FileInputStream("boluo.txt");
            ServerSocket serverSocket = new ServerSocket(8888);
        } finally {
            System.out.println("finally");
        }
    }
}
