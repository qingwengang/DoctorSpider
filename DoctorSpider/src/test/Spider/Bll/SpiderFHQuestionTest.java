package Spider.Bll;

import Spider.DO.Online.OnlineQuestionDo;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by qingwengang on 2016/7/5.
 */
public class SpiderFHQuestionTest {
    private SpiderFHQuestion handler=new SpiderFHQuestion(7,1);
    @Test
    public void testCreateQuestionImpl() throws Exception {
        List<OnlineQuestionDo> dos=handler.CreateQuestionImpl("'人流'",30);
        System.out.println(dos);
    }
}