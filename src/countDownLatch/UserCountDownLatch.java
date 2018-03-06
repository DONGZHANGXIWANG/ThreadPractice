package countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2017/12/19/019.
 */
public class UserCountDownLatch {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(2);  //线程需要被2次唤醒才继续工作

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.err.println("进入t1线程，等待其他线程执行完毕");
                    countDownLatch.await();
                    System.err.println("t1线程执行完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println("t2线程进行初始化操作");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println("t2线程初始化完毕，通知t1线程继续...");
                countDownLatch.countDown();
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println("t3线程进行初始化操作");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println("t3线程初始化完毕，通知t1线程继续...");
                countDownLatch.countDown();
            }
        });


        t1.start();
        t2.start();
        t3.start();
    }


}
