package futuremodel;

/**
 * Created by Administrator on 2017/11/28/028.
 */
public class FutureData implements Data{

    private RealData realData;

    private boolean isReady = false;

    @Override
    public synchronized String getRequest() {
        //如果没装载好 程序就一直处于阻塞状态
        while (!isReady){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //装载好直接获取数据即可
        return realData.getRequest();
    }

    public synchronized void setRealData(RealData realData) {
        if (isReady)
            return;
        //如果没装载,进行装载真实对象
        this.realData = realData;
        isReady = true;
        notify();
    }


}
