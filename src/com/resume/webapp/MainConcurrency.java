package com.resume.webapp;

public class MainConcurrency {
    private static int counter;
    private static final Object LOCK = new Object();
    private static final Object LOCK2 = new Object();
    private static int count;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + " ," + getState());
            }
        };
        thread0.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }
        }).start();
        System.out.println(thread0.getState());

        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    inc();
                }
            }).start();
        }
        Thread.sleep(700);
        System.out.println(counter);
        // MainConcurrency mainConcurrency = new MainConcurrency();
        Thread1 T1 = new Thread1();
        Thread2 T2 = new Thread2();
        T1.start();
        T2.start();
    }

    private static void inc() {

        synchronized (LOCK) {
            counter++;
        }
    }


    private static class Thread1 extends Thread {
        public void run() {
            synchronized (LOCK) {
                System.out.println("Thread 1: Holding lock 1...");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                System.out.println("Thread 1: Waiting for lock 2...");

                synchronized (LOCK2) {
                    System.out.println("Thread 1: Holding lock 1 & 2...");
                }
            }
        }
    }

    private static class Thread2 extends Thread {
        public void run() {
            synchronized (LOCK2) {
                System.out.println("Thread 2: Holding lock 2...");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                System.out.println("Thread 2: Waiting for lock 1...");

                synchronized (LOCK) {
                    System.out.println("Thread 2: Holding lock 1 & 2...");
                }
            }
        }
    }

}