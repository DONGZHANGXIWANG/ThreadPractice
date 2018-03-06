package futurejdk;

import java.util.concurrent.*;

/**
 * FutureTask 实现了 RunnableFuture,而RunnableFuture继承了Runnable接口和Future接口
 * 所以它既可以作为Runnable被线程执行，又可以作为Future得到Callable的返回值
 */
public class Test2 {

    public static void main(String[] args) {
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask(task);
        //第一种方式
        /*ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(futureTask);
        executor.shutdown();*/
        //第二种方式,利用FutureTask可以作为Runnable被线程执行的特点
        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程继续执行旧任务");

        try {
            System.out.println("通过future获取的结果:" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("所有任务执行完毕");

    }

}
