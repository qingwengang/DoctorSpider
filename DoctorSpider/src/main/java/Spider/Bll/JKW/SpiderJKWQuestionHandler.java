package Spider.Bll.JKW;

import Spider.Dao.BaseDao;
import Spider.Dao.JKWQuestionDao;
import Spider.Entity.JKWQuestion;
import SpiderFramework.Bll.SpiderHandler;
import Util.JsoupUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/17.
 */
public class SpiderJKWQuestionHandler extends SpiderHandler<JKWQuestion,JKWQuestion> {
    public SpiderJKWQuestionHandler() {
        super("获取jkw的问题信息");
        setGetUnspiderDataSql(MessageFormat.format("select * from jkwquestion where SpiderFlag=0  and id%{0}={1} LIMIT 0,10", 1, 0));
    }

    public SpiderJKWQuestionHandler( int threadCount, int threadNo) {
        super("获取jkw的问题信息", threadCount, threadNo, new JKWQuestionDao(),new JKWQuestionDao());
        setGetUnspiderDataSql(MessageFormat.format("select * from jkwquestion where SpiderFlag=0  and id%{0}={1} LIMIT 0,10", threadCount, threadNo));
    }

    @Override
    public void SpiderBll(JKWQuestion jkwQuestion) throws IOException {
        String url=MessageFormat.format("http://www.jianke.com/ask/question/{0}", jkwQuestion.getOutId());
        Document doc= JsoupUtil.GetDocument(url);
        String html=doc.html().replaceAll("(?i)<br[^>]*>", "卿文刚");
        doc= Jsoup.parse(html);
        String title=doc.select(".why > h1").get(0).text();
        String sDate=doc.select(".why > i").get(0).text();
        String sDesc=doc.select(".pd_txt").get(0).text();
        Elements answerDivs=doc.select(".an_cont");
        List<String> answer=new LinkedList<>();
        for(Element answerDiv : answerDivs){
            String answerString="";
            for(Element dl : answerDivs.select("dl")){
                for(Element dt : dl.select("dt")){
                    answerString+=dt.text()+"卿文刚";
                }
            }
            answer.add(answerString);
        }
        System.out.println(title);
        System.out.println(sDate);
        System.out.println(sDesc);
        System.out.println(answer);
    }
}
