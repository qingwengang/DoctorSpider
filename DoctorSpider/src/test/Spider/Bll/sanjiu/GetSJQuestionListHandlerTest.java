package Spider.Bll.sanjiu;

import Spider.Entity.Mulu;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/6/17.
 */
public class GetSJQuestionListHandlerTest {
    private GetSJQuestionListHandler handler=new GetSJQuestionListHandler();
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testSpiderBll() throws Exception {
        Mulu mulu=new Mulu();
        mulu.setUrl("/news/304-1.html");
        handler.SpiderBll(mulu);
    }
}