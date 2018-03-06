package masterworkermodel;

import java.util.Random;

/**
 * Created by Administrator on 2017/11/29/029.
 */
public class Main {

    public static void main(String[] args) {
        Master master = new Master(new Worker(), 20);

        Random random = new Random();
        for (int i = 1; i <= 100 ; i++) {
            Task t = new Task();
            t.setId(i);
            t.setPrice(random.nextInt(1000));
            master.submit(t);
        }

        master.execute();
        long start = System.currentTimeMillis();

        while (true){
            if (master.isComplete()){
                long end = System.currentTimeMillis() - start;
                int priceResult = master.getResult();
                System.out.println("最终结果：" + priceResult + ",执行时间：" + end);
                break;
            }
        }

    }

}
