package Spider.Start;

import Spider.Bll.Hdf.Thread.GetHdfPageDetailHandlerThread;

/**
 * Created by Administrator on 2016/6/12.
 */
public class HdfStart {
    public static  void main(String[] args){
        int handlerCount=1;
        for(int i=0;i<handlerCount;i++){
            GetHdfPageDetailHandlerThread th=new GetHdfPageDetailHandlerThread(handlerCount,i);
            Thread t=new Thread(th);
            t.start();
        }
    }
}
