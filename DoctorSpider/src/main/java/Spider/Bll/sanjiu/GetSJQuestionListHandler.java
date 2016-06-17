package Spider.Bll.sanjiu;

import Spider.Dao.BaseDao;
import Spider.Dao.MuluDao;
import Spider.Dao.SJQuestionDao;
import Spider.Entity.Mulu;
import Spider.Entity.SJQuestion;
import SpiderFramework.Bll.SpiderHandler;
import Util.BTreeUtil;
import Util.FormatUtil;
import Util.JsoupUtil;
import org.hibernate.annotations.SourceType;
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
public class GetSJQuestionListHandler extends SpiderHandler<Mulu,SJQuestion>{
    private SJQuestionDao questionDao=new SJQuestionDao();
    public GetSJQuestionListHandler() {
        super("获取三九问题列表");
    }
    public GetSJQuestionListHandler( int threadCount, int threadNo) {
        super("获取三九问题列表", threadCount, threadNo, new MuluDao(), new SJQuestionDao());
        setGetUnspiderDataSql(MessageFormat.format("select * from mulu where source='39' and level=1 and SpiderFlag=0 and id%{0}={1} limit 0,10", threadCount, threadNo));
    }

    private synchronized boolean Check(String outID){
        return BTreeUtil.CheckIfExist("39questionid",outID);
    }
    private synchronized void AddOutid(String outID){
        BTreeUtil.AddValue("39questionid",outID);
    }
    @Override
    public void SpiderBll(Mulu mulu) throws IOException {
        String url="http://ask.39.net/"+mulu.getUrl();
        Document doc= JsoupUtil.GetDocument(url);
        while(doc!=null){
            Element div=doc.select(".list_ask").get(0);
            Elements lis=div.getElementsByTag("li");
            String typeName=doc.select(".tag_top").get(0).getElementsByTag("h1").get(0).text();
            List<SJQuestion> toAddQuestions=new LinkedList<SJQuestion>();
            for(Element li: lis){
                Element href=li.getElementsByTag("a").get(0);
                if(!Check(href.attr("href"))){
                    SJQuestion question=new SJQuestion();
                    question.setTypeName(typeName);
                    String hUrl=href.attr("href");
                    String outId=hUrl.substring(hUrl.lastIndexOf("/")+1,hUrl.lastIndexOf("."));
                    question.setOutId(outId);
                    question.setSpiderFlag(0);
                    toAddQuestions.add(question);
                }else{
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }
            }
            questionDao.Add(toAddQuestions);
            Elements pageLinks=doc.select(".pgleft").get(0).getElementsByTag("a");
            doc=null;
            for(Element link : pageLinks){
                if(link.text().equals("下一页")){
                    url="http://ask.39.net/"+link.attr("href");
                    doc=JsoupUtil.GetDocument(url);
                }
            }
        }

    }
}
