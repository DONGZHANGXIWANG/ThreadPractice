package masterworkermodel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Administrator on 2017/11/29/029.
 */
public class Worker implements Runnable{

    private ConcurrentLinkedQueue<Task> workQueue;
    private ConcurrentHashMap<String, Object> resultMap;

    public void setWorkQueue(ConcurrentLinkedQueue<Task> workQueue) {
        this.workQueue = workQueue;
    }

    public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    @Override
    public void run() {
        while (true){
            Task task = this.workQueue.poll();
            if (task == null) break;
            Object output = handle(task);
            this.resultMap.put(Integer.toString(task.getId()), output);
        }
    }

    private Object handle(Task task) {
        Object output = null;
        try {
            //处理任务的耗时。。 比如说进行操作数据库。。。
            Thread.sleep(500);
            output = task.getPrice();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return output;
    }
}
