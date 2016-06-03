package Spider.Start;

import Spider.Bll.AskBll;
import Spider.Bll.GetQuestionList;
import Spider.Bll.SpiderAskQuestionHandler;

/**
 * Created by Administrator on 2016/6/2.
 */
public class AskStart {
    public static void main(String[] a){
//        AskBll askBll=new AskBll();
//        askBll.getMulu();

//        GetQuestionList getQuestionListHandler=new GetQuestionList();
//        getQuestionListHandler.DoSpider();

        SpiderAskQuestionHandler handler=new SpiderAskQuestionHandler();
        handler.DoSpider();
    }
}
