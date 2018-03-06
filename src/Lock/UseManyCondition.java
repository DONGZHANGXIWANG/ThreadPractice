package Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/12/25/025.
 */
public class UseManyCondition {

    private Lock lock = new ReentrantLock();

    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();


    public void m1(){
        try {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入等待");
            lock.lock();
            c1.await();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "被唤醒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void m2(){
        try {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入等待");
            lock.lock();
            c1.await();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "被唤醒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void m3(){
        try {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入等待");
            lock.lock();
            c2.await();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "被唤醒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void m4(){
        try {
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "唤醒...");
            c1.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void m5(){
        try {
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "唤醒...");
            c2.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final UseManyCondition manyCondition = new UseManyCondition();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                manyCondition.m1();
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                manyCondition.m2();
            }
        },"t2");

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                manyCondition.m3();
            }
        },"t3");

        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                manyCondition.m4();
            }
        },"t4");

        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                manyCondition.m5();
            }
        },"t5");

        t1.start();
        t2.start();
        t3.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t4.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t5.start();


    }


}
