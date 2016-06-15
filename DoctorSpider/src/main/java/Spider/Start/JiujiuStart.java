package Spider.Start;

import Spider.Bll.Thread.jiujiu.GetJiujiuQuestionListHandlerThread;
import Spider.Bll.Thread.jiujiu.SpiderJiujiuQuestionHandlerThread;
import Spider.Bll.jiujiu.GetJiujiuQuestionListHandler;

/**
 * Created by Administrator on 2016/6/13.
 */
public class JiujiuStart {

    public static void main(String[] args){
        //获取问题列表
//        GetList(3);
        //获取具体的问题信息
        GetQuestion(3);
    }
    public static void GetList(int count){
        for(int i=0;i<count;i++){
            GetJiujiuQuestionListHandlerThread th=new GetJiujiuQuestionListHandlerThread(count,i);
            Thread t=new Thread(th);
            t.start();
        }
    }
    public static  void GetQuestion(int count){
        for(int i=0;i<count;i++){
            SpiderJiujiuQuestionHandlerThread th=new SpiderJiujiuQuestionHandlerThread(count,i);
            Thread t=new Thread(th);
            t.start();
        }
    }
}
