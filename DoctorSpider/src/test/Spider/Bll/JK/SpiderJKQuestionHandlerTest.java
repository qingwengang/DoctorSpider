package Spider.Bll.JK;

import org.junit.Before;
import org.junit.Test;
import Spider.Entity.*;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/6/16.
 */
public class SpiderJKQuestionHandlerTest {
    private SpiderJKQuestionHandler handler=new SpiderJKQuestionHandler();
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testSpiderBll() throws Exception {
        JKQuestion question=new JKQuestion();
        question.setOutId(409737);
        question.setTypeName("fds");
        handler.SpiderBll(question);
    }
}