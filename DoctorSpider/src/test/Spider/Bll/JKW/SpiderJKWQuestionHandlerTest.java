package Spider.Bll.JKW;

import Spider.DO.Online.OnlineQuestionDo;
import Spider.Entity.JKWQuestion;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/6/17.
 */
public class SpiderJKWQuestionHandlerTest {
    private SpiderJKWQuestionHandler handler=new SpiderJKWQuestionHandler();
    @Test
    public void testSpiderBll() throws Exception {
        JKWQuestion question=new JKWQuestion();
        question.setOutId("11111");
        handler.SpiderBll(question);
    }

    @Test
    public void testCreateQuestionImpl() throws Exception {
        List<OnlineQuestionDo> dos=handler.CreateQuestionImpl("'疤痕修复'",2);
        System.out.println(dos);
    }
}