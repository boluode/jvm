package com.boluo.jvm.concurrency;

/**
 *
 * wait notify
 * 多个线程打印10101010101010
 */
public class ConCurrTest1 {

    public static void main(String[] args) throws InterruptedException {

        Calculate calculate = new Calculate();
        Thread thread1 = new Thread(() -> {
            int i = 0;
            while (i ++ < 100) {
                calculate.increase();
            }
        });

        Thread thread4 = new Thread(() -> {
            int i = 0;
            while (i ++ < 100) {
                calculate.increase();
            }
        });

        Thread thread2 = new Thread(() -> {
            int i = 0;
            while (i ++ < 100) {
                calculate.decrease();
            }
        });

        Thread thread3 = new Thread(() -> {
            int i = 0;
            while (i ++ < 100) {
                calculate.decrease();
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    static class Calculate {

        private int counter;

        private synchronized void increase() {

            try {
                while (counter != 0) {
                    wait(1000);
                }
                counter ++;
                System.out.print(counter);
                notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        private synchronized void decrease() {

            try {
                while (counter == 0) {
                    wait(1000);
                }
                counter --;
                System.out.print(counter);
                notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


