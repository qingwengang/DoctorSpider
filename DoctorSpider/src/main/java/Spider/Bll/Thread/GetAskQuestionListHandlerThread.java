package Spider.Bll.Thread;

import Spider.Bll.GetQuestionList;

/**
 * Created by Administrator on 2016/6/9.
 */
public class GetAskQuestionListHandlerThread implements Runnable {
    private int count, no;
    public GetAskQuestionListHandlerThread(int count, int no){
        this.count=count;
        this.no=no;
    }
    @Override
    public void run() {
        GetQuestionList getQuestionList=new GetQuestionList(count,no);
        getQuestionList.DoSpider();
    }
}
