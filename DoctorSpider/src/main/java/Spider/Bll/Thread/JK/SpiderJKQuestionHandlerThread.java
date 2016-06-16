package Spider.Bll.Thread.JK;

import Spider.Bll.JK.SpiderJKQuestionHandler;
import SpiderFramework.Bll.SpiderHandler;

/**
 * Created by Administrator on 2016/6/16.
 */
public class SpiderJKQuestionHandlerThread implements Runnable {
    private int count, no;
    public SpiderJKQuestionHandlerThread(int count, int no){
        this.count=count;
        this.no=no;
    }
    @Override
    public void run() {
        SpiderJKQuestionHandler handler=new SpiderJKQuestionHandler(count,no);
        handler.DoSpider();
    }
}
