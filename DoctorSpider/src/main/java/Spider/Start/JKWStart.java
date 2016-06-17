package Spider.Start;

import Spider.Bll.Thread.CommonHandlerThread;

/**
 * Created by Administrator on 2016/6/17.
 */
public class JKWStart {
    public static void main(String[] args){
        GetQuestion(3);
    }
    public static void GetQuestion(int count){
        for(int i=0;i<count;i++){
            CommonHandlerThread th=new CommonHandlerThread("spiderjkwquestion",count,i);
            Thread t=new Thread(th);
            t.start();
        }
    }
}
