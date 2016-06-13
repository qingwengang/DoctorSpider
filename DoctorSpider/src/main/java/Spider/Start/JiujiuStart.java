package Spider.Start;

import Spider.Bll.Thread.jiujiu.SpiderJiujiuQuestionHandlerThread;

/**
 * Created by Administrator on 2016/6/13.
 */
public class JiujiuStart {

    public static void main(String[] args){
        int handlerCount=1;
        for(int i=0;i<handlerCount;i++){
            SpiderJiujiuQuestionHandlerThread th=new SpiderJiujiuQuestionHandlerThread(handlerCount,i);
            Thread t=new Thread(th);
            t.run();
        }
    }
}
