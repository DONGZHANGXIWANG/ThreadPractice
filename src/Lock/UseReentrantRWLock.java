package Lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Administrator on 2017/12/25/025.
 */
public class UseReentrantRWLock {

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

    private ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();


    public void read(){
        try {
            readLock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入...");
            Thread.sleep(3000);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "退出...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readLock.unlock();
        }
    }

    public void write(){
        try {
            writeLock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "进入...");
            Thread.sleep(3000);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "退出...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        final UseReentrantRWLock rwLock = new UseReentrantRWLock();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                rwLock.read();
            }
        },"t1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                rwLock.read();
            }
        },"t2");

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                rwLock.write();
            }
        },"t3");

        thread1.start();
        //thread2.start();
        thread3.start();
    }

}
