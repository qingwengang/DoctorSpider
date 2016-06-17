package Spider.Bll.sanjiu;

import Spider.Entity.SJQuestion;
import org.junit.Test;

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
}