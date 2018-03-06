package Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/12/25/025.
 */
public class UserCondition {

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();


    public void method1(){
        try {
            System.err.println("t1线程开始执行");
            lock.lock();
            Thread.sleep(2000);
            System.err.println("t1线程释放锁");
            condition.await();
            System.err.println("t1线程继续执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void method2(){
        try {
            System.err.println("t2线程开始执行");
            lock.lock();
            Thread.sleep(2000);
            System.err.println("t2线程开始唤醒");
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final UserCondition userCondition = new UserCondition();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                userCondition.method1();
            }
        },"t1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                userCondition.method2();
            }
        },"t2");

        thread1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
