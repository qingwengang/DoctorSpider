package Spider.Start;

import Spider.Dao.FHQuesionDao;
import Spider.Entity.FHQuestion;

/**
 * Created by Administrator on 2016/6/11.
 */
public class DeleteLater {
    public static void main(String[] args){

            FHQuesionDao quesionDao=new FHQuesionDao();
            for(long i=100474432;i<102000001;i++){
                FHQuestion question=new FHQuestion();
                question.setOutId(((Long)i).toString());
                quesionDao.Add(question);
            }

    }
}
