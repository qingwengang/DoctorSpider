package Spider.Bll.jiujiu;

import Spider.DO.Online.OnlineQuestionDo;
import Spider.Entity.JjQuestion;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/6/15.
 */
public class SpiderJiujiuQuestionHandlerTest {
    private SpiderJiujiuQuestionHandler handler=new SpiderJiujiuQuestionHandler();
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testSpiderBll() throws Exception {
        JjQuestion question=new JjQuestion();
        question.setOutId(3373800);
        handler.SpiderBll(question);
    }


    @Test
    public void testCreateQuestionImpl() throws Exception {
        List<OnlineQuestionDo> dos=handler.CreateQuestionImpl("'哮喘'",5);
        System.out.println(dos);
    }
}