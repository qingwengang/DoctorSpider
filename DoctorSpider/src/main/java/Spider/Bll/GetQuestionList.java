package Spider.Bll;

import Spider.Dao.AskQuestionDao;
import Spider.Dao.MuluDao;
import Spider.Entity.AskQuestion;
import Spider.Entity.Mulu;
import SpiderFramework.Bll.SpiderHandler;
import SpiderFramework.Entity.BaseSpiderEntity;
import Util.BTreeUtil;
import Util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * 获取120ask的问题列表
 */
public class GetQuestionList extends SpiderHandler<Mulu> {
    private MuluDao muluDao=new MuluDao();
    private AskQuestionDao askQuestionDao=new AskQuestionDao();
    public GetQuestionList() {
        super("获取120ask的问题列表");
    }
    @Override
    public List<Mulu> getUnspiderData() {
        String sql="select * from mulu where source='120ask' and SpiderFlag=0 limit 0,10";
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
                System.out.print(e);
            }
        }
    }

    @Override
    public void Update(Mulu mulu) {
        muluDao.Update(mulu);
    }
}
