package Spider.Bll.Thread;

import Spider.Bll.SpiderFHQuestion;
import Util.LogUtil;

/**
 * Created by Administrator on 2016/6/10.
 */
public class SpiderFHQuestionThreader implements Runnable {
    public SpiderFHQuestionThreader(int count,int no){
        handler=new SpiderFHQuestion(count,no);
    }
    private SpiderFHQuestion handler;
    @Override
    public void run() {
        boolean flag=true;
        while (flag){
            try {
                handler.DoSpider();
                flag=false;
            } catch (Exception e) {
                LogUtil.Log120Ask("SpiderFHQuestionThreader线程：" + handler.getThreadNo() + "意外终止，" + e.getStackTrace()+e.getMessage());
                flag=true;
            }
        }

    }
}
