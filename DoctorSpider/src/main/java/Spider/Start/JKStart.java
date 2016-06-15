package Spider.Start;

import Spider.Bll.Thread.JK.GetJKQuestionListHandlerThread;

/**
 * Created by Administrator on 2016/6/15.
 */
public class JKStart {
    public static void main(String[] args){
        //获取问题列表
        GetList(3);
        //获取具体的问题信息
//        GetQuestion(3);
    }
    public static void GetList(int count){
        for(int i=0;i<count;i++){
            GetJKQuestionListHandlerThread th=new GetJKQuestionListHandlerThread(count,i);
            Thread t=new Thread(th);
            t.start();
        }
    }
}
