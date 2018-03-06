package futuremodel;

/**
 * Created by Administrator on 2017/11/28/028.
 */
public class FutureClient {

    public Data request(final String queryStr){
        //1.调用包装类,先返回假的结果,在开启线程去查询真正的结果
        final FutureData futureData = new FutureData();
        //2.开启新的线程,加载真实数据,传递给这个代理对象
        new Thread(new Runnable() {
            @Override
            public void run() {
                //3.这个新线程可以去慢慢的加载真实对象,然后传递给代理对象
                RealData realData = new RealData(queryStr);
                futureData.setRealData(realData);
            }
        }).start();
        return futureData;
    }

}
