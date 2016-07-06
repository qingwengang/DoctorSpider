package Spider.Bll.GenerateQuestion;

import Spider.Bll.SpiderAskQuestionHandler;
import Spider.DO.Online.OnlineQuestionDo;
import Spider.Dao.OnlineMuluRelationDao;
import Spider.Entity.OnlineMuluRelation;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by qingwengang on 2016/7/4.
 */
public class CreateQuestionBll {
    private OnlineMuluRelationDao relationDao=new OnlineMuluRelationDao();
    public void CreateQuestion(long muluId){
        List<List<OnlineQuestionDo>> questionQueueList=new LinkedList<>();
        OnlineMuluRelation relation=relationDao.GetByOnlineMuluId(muluId);
        List<OnlineQuestionDo> asks=new SpiderAskQuestionHandler().CreateQuestion(relation.getAsk120Relation(),5);
        questionQueueList.add(asks);

    }
}
