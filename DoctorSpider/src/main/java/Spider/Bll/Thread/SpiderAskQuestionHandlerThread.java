package Spider.Bll.Thread;

import Spider.Bll.SpiderAskQuestionHandler;
import Util.LogUtil;

/**
 * Created by Administrator on 2016/6/9.
 */
public class SpiderAskQuestionHandlerThread implements Runnable {
    private int count, no;
    public SpiderAskQuestionHandlerThread(int count,int no){
        this.count=count;
        this.no=no;
    }
    @Override
    public void run() {
        boolean flag=true;
        while (flag){
            try {
                SpiderAskQuestionHandler handler = new SpiderAskQuestionHandler(count, no);
                handler.DoSpider();
                flag=false;
            } catch (Exception e) {
                LogUtil.Log120Ask("线程："+no+"意外终止，"+e.getStackTrace()+e.getMessage());
                flag=true;
            }
        }
    }
}
