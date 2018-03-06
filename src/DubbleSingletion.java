/**
 * 两次确认的单例模式
 * 加锁确保只有一个线程创建对象
 * 加锁后判断是怕有多个线程处于初始化对象中,不加判断的话每个线程就都会创建对象
 */
public class DubbleSingletion {

    private volatile static DubbleSingletion ds;

    public static DubbleSingletion getDs() {
        if (ds == null) {
            try {
                //模拟初始化对象的耗时
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (DubbleSingletion.class) {
                //加锁后再判断一次
                if (ds == null) {
                    ds = new DubbleSingletion();
                }
            }
        }
        return ds;
    }


}
