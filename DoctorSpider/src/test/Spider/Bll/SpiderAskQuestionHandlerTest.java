package Spider.Bll;

import Spider.DO.Online.OnlineQuestionDo;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by qingwengang on 2016/7/4.
 */
public class SpiderAskQuestionHandlerTest {
    private SpiderAskQuestionHandler handler=new SpiderAskQuestionHandler();
    @Test
    public void testCreateQuestion() throws Exception {
        List<OnlineQuestionDo> dos=handler.CreateQuestion("guke",3);
    }


}