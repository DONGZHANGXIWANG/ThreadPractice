package futurejdk;

import java.util.concurrent.Callable;

/**
 * Callable位于java.util.concurrent包下,他是一个接口,在它里面也只生命了一个方法:call()
 * call()方法是一个泛型接口,返回的类型就是传递进来的V类型
 * Callable一般配合ExecutorService来使用的,ExecutorService接口中声明了若干个submit方法的重载
 * 其中有一个<T> Future<T> submit(Callable<T> task);就是用于Callable
 */
public class Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0 ;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }

}
