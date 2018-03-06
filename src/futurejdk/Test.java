package futurejdk;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *  Future就是对具体的Runnable或者Callable任务的执行结果进行取消、查询是否完成、获取结果。
 * 必要时可以通过get()方法获取执行结果，该方法会阻塞直到任务返回结果
 * future主要提供了3种功能
 *      1.判断任务是否完成
 *      2.能够中断任务
 *      3.能够获取任务执行结果
 *  下面示例：使用Callable和Future获取执行结果
 */
public class Test {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        System.out.println("主线程创建新任务发出");
        Future<Integer> result = executor.submit(task);
        executor.shutdown();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程继续执行旧任务");

        try {
            System.out.println("通过future获取的结果:" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("所有任务执行完毕");

    }

}
