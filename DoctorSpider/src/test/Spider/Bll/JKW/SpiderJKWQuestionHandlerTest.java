package Spider.Bll.JKW;

import Spider.Entity.JKWQuestion;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/6/17.
 */
public class SpiderJKWQuestionHandlerTest {
    private SpiderJKWQuestionHandler handler=new SpiderJKWQuestionHandler();
    @Test
    public void testSpiderBll() throws Exception {
        JKWQuestion question=new JKWQuestion();
        question.setOutId("13710762");
        handler.SpiderBll(question);
    }
}