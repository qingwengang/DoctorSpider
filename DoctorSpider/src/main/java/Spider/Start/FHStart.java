package Spider.Start;

import Spider.Bll.FHBll;
import Spider.Bll.Thread.SpiderFHQuestionThreader;
import Spider.Dao.FHQuesionDao;
import Spider.Entity.FHQuestion;

/**
 * Created by Administrator on 2016/6/8.
*/
public class FHStart {
    public static void main(String[] args){
//        InitQuestionId();
        //获取FH的问题内容
        int handlerCount=8;
        for(int i=0;i<handlerCount;i++){
            SpiderFHQuestionThreader th=new SpiderFHQuestionThreader(handlerCount,i);
            Thread t=new Thread(th);
            t.start();
        }
    }
    private static void InitQuestionId(){
        FHQuesionDao quesionDao=new FHQuesionDao();
        for(long i=100000001;i<102000001;i++){
            FHQuestion question=new FHQuestion();
            question.setOutId(((Long)i).toString());
            quesionDao.Add(question);
        }
    }
}
