package Spider.Bll.Thread.JK;

import Spider.Bll.JK.GetJKQuestionListHandler;

/**
 * Created by Administrator on 2016/6/15.
 */
public class GetJKQuestionListHandlerThread implements Runnable {
    private int count, no;
    public GetJKQuestionListHandlerThread(int count, int no){
        this.count=count;
        this.no=no;
    }
    @Override
    public void run() {
        GetJKQuestionListHandler handler=new GetJKQuestionListHandler(count,no);
        handler.DoSpider();
    }
}
