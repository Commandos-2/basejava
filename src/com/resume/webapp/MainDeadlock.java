package com.resume.webapp;

public class MainDeadlock {
    private static final Object LOCK = new Object();
    private static final Object LOCK2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread T1 = new Thread(() -> {
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
        });
        Thread T2 = new Thread(() -> {
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
        );
        T1.start();
        T2.start();
    }
}
