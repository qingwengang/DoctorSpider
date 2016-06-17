package Spider.Bll.Thread;

import Spider.Bll.JKW.SpiderJKWQuestionHandler;
import Spider.Bll.sanjiu.GetSJQuestionListHandler;
import Spider.Bll.sanjiu.SpiderSJQuestionHandler;
import SpiderFramework.Bll.SpiderHandler;

/**
 * Created by Administrator on 2016/6/17.
 */
public class CommonHandlerThread implements Runnable {
    private int count, no;
    private String type;
    private SpiderHandler handler;
    public CommonHandlerThread(String type,int count,int no) {
        this.count=count;
        this.no=no;
        this.type=type;
    }

    @Override
    public void run() {
        switch (type){
            case "get39questionList":
                handler=new GetSJQuestionListHandler(count,no);
                break;
            case "spider39question":
                handler=new SpiderSJQuestionHandler(count,no);
                break;
            case "spiderjkwquestion":
                handler=new SpiderJKWQuestionHandler(count,no);
                break;
        }
        handler.DoSpider();
    }
}
