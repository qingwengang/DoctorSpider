package Spider.Bll.JK;

import Spider.Dao.BaseDao;
import Spider.Dao.JKQuestionDao;
import Spider.Entity.JKQuestion;
import SpiderFramework.Bll.BaseHandlerThread;
import SpiderFramework.Bll.SpiderHandler;
import Util.JsoupUtil;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.MessageFormat;

/**
 * Created by Administrator on 2016/6/15.
 */
public class SpiderJKQuestionHandler extends SpiderHandler<JKQuestion,JKQuestion> {
    public SpiderJKQuestionHandler() {
        super("获取JK问题信息",1,0);
        setGetUnspiderDataSql(MessageFormat.format("select * from jkquestion where SpiderFlag=0  and id%{0}={1} LIMIT 0,10", 1, 0));
    }

    public SpiderJKQuestionHandler(int threadCount, int threadNo) {
        super("获取JK问题信息", threadCount, threadNo, new JKQuestionDao(), new JKQuestionDao());
        setGetUnspiderDataSql(MessageFormat.format("select * from jkquestion where SpiderFlag=0  and id%{0}={1} LIMIT 0,10", threadCount, threadNo));
    }

    @Override
    public void SpiderBll(JKQuestion jkQuestion) throws IOException {
        String url=MessageFormat.format("http://www.jiankang.com/ask/{0}.shtml",jkQuestion.getOutId());
        Document doc= JsoupUtil.GetDocument(url);
    }
}
