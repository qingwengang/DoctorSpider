package Spider.Bll.Thread.jiujiu;
import Spider.Bll.jiujiu.*;
/**
 * Created by Administrator on 2016/6/13.
 */
public class SpiderJiujiuQuestionHandlerThread implements Runnable{
    private int count, no;
    public SpiderJiujiuQuestionHandlerThread(int count, int no){
        this.count=count;
        this.no=no;
    }
    @Override
    public void run() {
        SpiderJiujiuQuestionHandler handler=new SpiderJiujiuQuestionHandler(count,no);
        handler.DoSpider();
    }
}
