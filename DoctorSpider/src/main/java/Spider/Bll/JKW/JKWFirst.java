package Spider.Bll.JKW;

import Spider.Dao.JKWQuestionDao;
import Spider.Entity.JKWQuestion;

/**
 * Created by Administrator on 2016/6/17.
 */
public class JKWFirst {
    private static JKWQuestionDao dao=new JKWQuestionDao();
    public static void main(String[] args){
        for(int i=3031111;i<3032131;i++){
            JKWQuestion question=new JKWQuestion();
            question.setOutId(Long.toString(i));
            question.setSpiderFlag(0);
            dao.Add(question);
        }
    }
}
