package Spider.Bll.Thread;

import Spider.Bll.SpiderAskQuestionHandler;
import Spider.Bll.SpiderAskQuestionHandler2;
import Util.LogUtil;

/**
 * Created by Administrator on 2016/6/9.
 */
public class SpiderAskQuestionHandlerThread2 implements Runnable {
    private int count, no;
    public SpiderAskQuestionHandlerThread2(int count, int no){
        this.count=count;
        this.no=no;
    }
    @Override
    public void run() {
        boolean flag=true;
        while (flag){
            try {
                SpiderAskQuestionHandler2 handler = new SpiderAskQuestionHandler2(count, no);
                handler.DoSpider();
                flag=false;
            } catch (Exception e) {
                LogUtil.Log120Ask("线程："+no+"意外终止，"+e.getStackTrace()+e.getMessage());
                flag=true;
            }
        }
    }
}
