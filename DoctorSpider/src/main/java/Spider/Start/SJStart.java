package Spider.Start;

import Spider.Bll.Thread.CommonHandlerThread;

/**
 * Created by Administrator on 2016/6/17.
 */
public class SJStart {
    public static void main(String[] args){
//        GetList(3);
        GetQuestion(3);
    }
    public static void GetList(int count){
        for(int i=0;i<count;i++){
            CommonHandlerThread th=new CommonHandlerThread("get39questionList",count,i);
            Thread t=new Thread(th);
            t.start();
        }
    }
    public static void GetQuestion(int count){
        for(int i=0;i<count;i++){
            CommonHandlerThread th=new CommonHandlerThread("spider39question",count,i);
            Thread t=new Thread(th);
            t.start();
        }
    }
}
