package Spider.Bll.Thread.jiujiu;

import Spider.Bll.jiujiu.GetJiujiuQuestionListHandler;

/**
 * Created by Administrator on 2016/6/15.
 */
public class GetJiujiuQuestionListHandlerThread implements Runnable {
    private int count, no;
    public GetJiujiuQuestionListHandlerThread(int count, int no){
        this.count=count;
        this.no=no;
    }
    @Override
    public void run() {
        GetJiujiuQuestionListHandler handler=new GetJiujiuQuestionListHandler(count,no);
        handler.DoSpider();
    }
}
