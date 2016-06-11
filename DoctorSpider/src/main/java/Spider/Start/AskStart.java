package Spider.Start;

import Spider.Bll.Thread.GetAskQuestionListHandlerThread;
import Spider.Bll.Thread.SpiderAskQuestionHandlerThread;
import Spider.Bll.Thread.SpiderAskQuestionHandlerThread2;

/**
 * Created by Administrator on 2016/6/2.
 */
public class AskStart {
    public static void main(String[] a){
        //获取120ask的目录
//        AskBll askBll=new AskBll();
//        askBll.getMulu();
        //获取120ask的问题列表
//        getQuestionListHandler.DoSpider();
//        int getQuesionListhandlerCount=10;
//        for(int i=0;i<getQuesionListhandlerCount;i++){
//            GetAskQuestionListHandlerThread th=new GetAskQuestionListHandlerThread(getQuesionListhandlerCount,i);
//            Thread t=new Thread(th);
//            t.start();
//        }
        //获取120ask的问题内容
//        int handlerCount=10;
//        for(int i=0;i<handlerCount;i++){
//            SpiderAskQuestionHandlerThread th=new SpiderAskQuestionHandlerThread(handlerCount,i);
//            Thread t=new Thread(th);
//            t.start();
//        }
        //获取120ask的问题内容(补丁版)
        int handlerCount=10;
        for(int i=0;i<handlerCount;i++){
            SpiderAskQuestionHandlerThread2 th=new SpiderAskQuestionHandlerThread2(handlerCount,i);
            Thread t=new Thread(th);
            t.start();
        }
    }
}
