package Lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/12/25/025.
 */
public class UseReentrantLock {

    final ReentrantLock lock = new ReentrantLock();

    public void method1 (){
        try {
            lock.lock();
            System.err.println("当前线程：" + Thread.currentThread().getName() + " 进入method1方法");
            Thread.sleep(2000);
            System.err.println("当前线程：" + Thread.currentThread().getName() + " 退出method1方法");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void method2 (){
        try {
            lock.lock();
            System.err.println("当前线程：" + Thread.currentThread().getName() + " 进入method2方法");
            Thread.sleep(2000);
            System.err.println("当前线程：" + Thread.currentThread().getName() + " 退出method2方法");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final UseReentrantLock useReentrantLock = new UseReentrantLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                useReentrantLock.method1();
                useReentrantLock.method2();
            }
        }, "t1");

        t1.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
