package com.resume.webapp;

public class MainDeadlock {
    private static final Object LOCK = new Object();
    private static final Object LOCK2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread T1 = new Thread(() -> metod("Thread1", LOCK, LOCK2));
        Thread T2 = new Thread(() -> metod("Thread2", LOCK2, LOCK));
        T1.start();
        T2.start();
    }

    private static void metod(String thread, Object x, Object y) {
        synchronized (x) {
            System.out.println(thread + ": Holding lock...");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            System.out.println(thread + ": Waiting for lock...");
            synchronized (y) {
                System.out.println("Thread: Holding lock 1 & 2...");
            }
        }
    }
}
