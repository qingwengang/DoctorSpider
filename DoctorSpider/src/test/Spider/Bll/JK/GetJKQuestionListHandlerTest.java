package Spider.Bll.JK;

import Spider.Entity.Mulu;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/6/15.
 */
public class GetJKQuestionListHandlerTest {
    private GetJKQuestionListHandler handler=new GetJKQuestionListHandler();
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testSpiderBll() throws Exception {
        Mulu mulu=new Mulu();
        mulu.setUrl("/ask/bm/");
        handler.SpiderBll(mulu);
    }
}