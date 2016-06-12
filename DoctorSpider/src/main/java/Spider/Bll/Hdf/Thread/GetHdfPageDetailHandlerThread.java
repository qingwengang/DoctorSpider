package Spider.Bll.Hdf.Thread;

import Spider.Bll.Hdf.GetHdfPageDetailHandler;
import Util.LogUtil;

/**
 * Created by Administrator on 2016/6/12.
 */
public class GetHdfPageDetailHandlerThread implements Runnable{
    private GetHdfPageDetailHandler handler;
    public GetHdfPageDetailHandlerThread(int count,int no){
        handler=new GetHdfPageDetailHandler(count,no);
    }
    @Override
    public void run() {
        boolean flag=true;
        while (flag){
            try {
                handler.DoSpider();
                flag=false;
            } catch (Exception e) {
                LogUtil.Log120Ask("GetHdfPageDetailHandlerThread：" + handler.getThreadNo() + "意外终止，" + e.getStackTrace() + e.getMessage());
                flag=true;
            }
        }

    }
}
