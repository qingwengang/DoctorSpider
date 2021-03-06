package Spider.Bll;

import Spider.DO.Online.OnlineQuestionDo;
import Spider.Dao.AskQuestionDao;
import Spider.Dao.MuluDao;
import Spider.Entity.AskQuestion;
import Spider.Entity.Mulu;
import SpiderFramework.Bll.SpiderHandler;
import SpiderFramework.Entity.BaseSpiderEntity;
import Util.BTreeUtil;
import Util.JsoupUtil;
import Util.Log4jTest;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 获取120ask的问题列表
 */
public class GetQuestionList extends SpiderHandler<Mulu,Mulu> {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Log4jTest.class);
    private MuluDao muluDao=new MuluDao();
    private AskQuestionDao askQuestionDao=new AskQuestionDao();
    public GetQuestionList(int threadCount,int threadNo){
        super("获取120ask的问题列表",threadCount,threadNo,new MuluDao(),new MuluDao());
    }
    @Override
    public List<Mulu> getUnspiderData() {
        String sql= "select * from mulu where source='120ask' and SpiderFlag=0 and id%"+getThreadCount()+"="+getThreadNo()+" limit 0,10";
        List<Mulu> mulus=muluDao.Query(sql);
        return mulus;
    }

    @Override
    public void SpiderBll(Mulu mulu) {
        String url = "http://www.120ask.com/list/" + mulu.getType() + "/all/1";
        Document doc = JsoupUtil.GetDocument(url);
        Elements lis = doc.select(".q-quename");
        Element pagDiv = doc.select(".h-page").get(0);
        Elements pages = pagDiv.getElementsByTag("a");
        int pageCount = 1;
        for (Element e : pages) {
            if (e.text().contains("最后一页")) {
                try {
                    String lastPageUrl = e.attr("href");
                    pageCount = Integer.parseInt(lastPageUrl.substring(lastPageUrl.indexOf("/all") + 5, lastPageUrl.lastIndexOf("/")));
                } catch (Exception ex) {
                    logger.error("GetQuestionList1",ex);
                }
            }
        }
        for (int i = pageCount; i >= 1; i--) {
            try {
                url = "http://www.120ask.com/list/" + mulu.getType() + "/all/" + i;
                doc = JsoupUtil.GetDocument(url);
                    lis = doc.select(".q-quename");
                    for (Element li : lis) {
                        String questionUrl = li.attr("href");
                        String askId = questionUrl.substring(questionUrl.lastIndexOf("/") + 1, questionUrl.lastIndexOf("."));
                        if (!BTreeUtil.CheckIfExist("askId", askId)) {
                            AskQuestion quesion = new AskQuestion();
                            quesion.setOutId(askId);
                            askQuestionDao.Add(quesion);
                            BTreeUtil.AddValue("askId", askId);
                        }
                }
            } catch (Exception e) {
                logger.error("GetQuestionList2",e);
            }
        }
    }

    public List<OnlineQuestionDo> CreateOnlineQuestion(String mulu,int count){
        List<OnlineQuestionDo> result=new LinkedList<>();
        return result;
    }

}
