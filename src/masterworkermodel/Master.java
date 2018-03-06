package masterworkermodel;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Administrator on 2017/11/29/029.
 */
public class Master {

    //1.创建一个盛放任务的容器
    private ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<Task>();

    //2.需要一个存放worker的集合
    private HashMap<String, Thread> workers = new HashMap<>();

    //3.需要一个存放每一个worker执行任务的结果集合
    private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();


    //4.构造方法
    public Master(Worker worker, int workerCount){
        worker.setWorkQueue(this.workQueue);
        worker.setResultMap(this.resultMap);
        //创建work线程
        for (int i = 0; i < workerCount; i++) {
            this.workers.put(Integer.toString(i), new Thread(worker));
        }
    }

    //5.需要一个提交任务的方法
    public void submit(Task task){
        this.workQueue.add(task);
    }

    //6.需要一个执行的方法，启动所有的worker方法去执行任务
    public void execute(){
        for (Map.Entry<String, Thread> me : workers.entrySet()) {
            me.getValue().start();
        }
    }

    public boolean isComplete() {
        for (Map.Entry<String, Thread> em : workers.entrySet()){
            if(em.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }

    public int getResult() {
        int priceResult = 0;
        for(Map.Entry<String, Object> me : resultMap.entrySet()){
            priceResult += (Integer)me.getValue();
        }
        return priceResult;
    }
}
