package Spider.Start;

import Spider.Bll.AskBll;
import Spider.Bll.GetQuestionList;
import Spider.Bll.SpiderAskQuestionHandler;

/**
 * Created by Administrator on 2016/6/2.
 */
public class AskStart {
    public static void main(String[] a){
        //获取120ask的目录
//        AskBll askBll=new AskBll();
//        askBll.getMulu();
        //获取120ask的问题列表
        GetQuestionList getQuestionListHandler=new GetQuestionList();
        getQuestionListHandler.DoSpider();
        //获取120ask的问题内容
//        SpiderAskQuestionHandler handler=new SpiderAskQuestionHandler();
//        handler.DoSpider();
    }
}
