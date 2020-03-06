package com.zm.LeetCodeEx.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * 1114. 按序打印
 * 我们提供了一个类：
 * <p>
 * public class Foo {
 *   public void one() { print("one"); }
 *   public void two() { print("two"); }
 *   public void three() { print("three"); }
 * }
 * 三个不同的线程将会共用一个 Foo 实例。
 * <p>
 * 线程 A 将会调用 one() 方法
 * 线程 B 将会调用 two() 方法
 * 线程 C 将会调用 three() 方法
 * 请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: "onetwothree"
 * 解释:
 * 有三个线程会被异步启动。
 * 输入 [1,2,3] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 two() 方法，线程 C 将会调用 three() 方法。
 * 正确的输出是 "onetwothree"。
 * 示例 2:
 * <p>
 * 输入: [1,3,2]
 * 输出: "onetwothree"
 * 解释:
 * 输入 [1,3,2] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 three() 方法，线程 C 将会调用 two() 方法。
 * 正确的输出是 "onetwothree"。
 *  
 * <p>
 * 注意:
 * <p>
 * 尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。
 * <p>
 * 你看到的输入格式主要是为了确保测试的全面性。
 *
 * @author zm
 */
public class LEET1114 {
    public static void main(String[] args) {
        int[] order = {2, 1, 3};
        LEET1114 leet1114 = new LEET1114();
        for (int num : order) {
            if (num == 1) {
                new Thread(new LEET1114Thread1(leet1114)).start();
            } else if (num == 2) {
                new Thread(new LEET1114Thread2(leet1114)).start();
            } else {
                new Thread(new LEET1114Thread3(leet1114)).start();
            }
        }
    }

    /*
    // 方法1：使用信号量
    public Semaphore seam_first_two = new Semaphore(0);

    public Semaphore seam_two_second = new Semaphore(0);

    public LEET1114() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        seam_first_two.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        seam_first_two.acquire();
        printSecond.run();
        seam_two_second.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        seam_two_second.acquire();
        printThird.run();
    }*/

    /*
    // 方法2：模拟CAS自旋
    private volatile int task = 1;

    public LEET1114() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        while (true) {
            System.out.println("first try...");
            if (task == 1) {
                printFirst.run();
                task = 2;
                break;
            }
        }

    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (true) {
            System.out.println("second try...");
            if (task == 2) {
                printSecond.run();
                task = 3;
                break;
            }
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (true) {
            System.out.println("third try...");
            if (task == 3) {
                printThird.run();
                break;
            }
        }
    }*/

    private CountDownLatch countDownLatch1 = new CountDownLatch(1);
    private CountDownLatch countDownLatch2 = new CountDownLatch(1);

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        countDownLatch1.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        countDownLatch1.await();
        printSecond.run();
        countDownLatch2.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        countDownLatch2.await();
        printThird.run();
    }
}

class LEET1114Print implements Runnable {
    String outPutStr;

    LEET1114Print(String str) {
        outPutStr = str;
    }

    @Override
    public void run() {
        System.out.println(outPutStr);
    }
}

class LEET1114Thread1 implements Runnable {
    private LEET1114 leet1114;

    LEET1114Thread1(LEET1114 leet1114) {
        this.leet1114 = leet1114;
    }

    @Override
    public synchronized void run() {
        try {
            leet1114.first(new LEET1114Print("first"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class LEET1114Thread2 implements Runnable {
    private LEET1114 leet1114;

    LEET1114Thread2(LEET1114 leet1114) {
        this.leet1114 = leet1114;
    }

    @Override
    public synchronized void run() {
        try {
            leet1114.second(new LEET1114Print("second"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class LEET1114Thread3 implements Runnable {
    private LEET1114 leet1114;

    LEET1114Thread3(LEET1114 leet1114) {
        this.leet1114 = leet1114;
    }

    @Override
    public synchronized void run() {
        try {
            leet1114.third(new LEET1114Print("third"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

