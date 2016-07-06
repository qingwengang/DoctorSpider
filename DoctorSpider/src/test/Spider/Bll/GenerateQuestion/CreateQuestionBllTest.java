package Spider.Bll.GenerateQuestion;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by qingwengang on 2016/7/4.
 */
public class CreateQuestionBllTest {
    private  CreateQuestionBll bll=new CreateQuestionBll();
    @Test
    public void testCreateQuestion() throws Exception {
        bll.CreateQuestion(3);
    }
}