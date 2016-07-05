package Spider.Bll.sanjiu;

import Spider.DO.Online.OnlineQuestionDo;
import Spider.Entity.SJQuestion;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/6/17.
 */
public class SpiderSJQuestionHandlerTest {
    private SpiderSJQuestionHandler handler=new SpiderSJQuestionHandler();
    @Test
    public void testSpiderBll() throws Exception {
        SJQuestion question=new SJQuestion();
        question.setOutId("43870746");
        handler.SpiderBll(question);
    }

    @Test
    public void testCreateQuestionImpl() throws Exception {
        List<OnlineQuestionDo> dos=handler.CreateQuestionImpl("'普外科'",5);
        System.out.println(dos);
    }
}