package Spider.Bll.jiujiu;

import Spider.Entity.Mulu;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/6/15.
 */
public class GetJiujiuQuestionListHandlerTest {
    private Mulu mulu;
    GetJiujiuQuestionListHandler handler=new GetJiujiuQuestionListHandler(1,0);
    @Before
    public void setUp() throws Exception {
        mulu=new Mulu();
        mulu.setUrl("/classid/34");
        handler.SpiderBll(mulu);
    }

    @Test
    public void testSpiderBll() throws Exception {

    }
}