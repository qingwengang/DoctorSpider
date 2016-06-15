package Spider.Bll.JK;

import Spider.Dao.BaseDao;
import Spider.Dao.JKQuestionDao;
import Spider.Dao.MuluDao;
import Spider.Entity.JKQuestion;
import Spider.Entity.Mulu;
import SpiderFramework.Bll.SpiderHandler;
import Util.FormatUtil;
import Util.JsoupUtil;
import org.hibernate.annotations.SourceType;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/15.
 */
public class GetJKQuestionListHandler extends SpiderHandler<Mulu,JKQuestion> {
    private JKQuestionDao dao=new JKQuestionDao();
    public GetJKQuestionListHandler() {
        super("获取JK列表");
    }

    public GetJKQuestionListHandler( int threadCount, int threadNo) {
        super("获取JK列表", threadCount, threadNo, new MuluDao(), new JKQuestionDao());
        setGetUnspiderDataSql(MessageFormat.format("select * from mulu where source=''JK'' and level=3 and SpiderFlag=0 and id%{0}={1} limit 0,10", threadCount, threadNo));
    }

    @Override
    public void SpiderBll(Mulu mulu) throws IOException {
        String url="http://www.jiankang.com"+mulu.getUrl();
        Document doc= JsoupUtil.GetDocument(url);
        LinkedList<Long> outIds=new LinkedList<Long>();
        while(doc!=null){
            Element tab=doc.select(".Js_questions_list").get(0);
            Elements lis=tab.getElementsByTag("li");
            List<JKQuestion> toAddQuestions=new LinkedList<>();
            for(Element li : lis){
                Element tagHref=li.select(".sort").get(0);
                String tagName=tagHref.text().replace("[","").replace("]","");
                Element href=li.select(".headline").get(0);
                String title=href.text();
                String qurl=href.attr("href");
                Long outId=Long.parseLong(qurl.substring(qurl.lastIndexOf('/') + 1, qurl.lastIndexOf('.')));
                String pTime=li.select(".last_date").get(0).text();
                JKQuestion question=new JKQuestion();
                question.setOutId(outId);
                question.setTypeName(tagName);
                question.setSpiderFlag(0);
                try {
                    question.setPublishTime(FormatUtil.GetDateByString(pTime));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(!outIds.contains(outId)){
                    toAddQuestions.add(question);
                    outIds.add(outId);
                }
            }
            dao.Add(toAddQuestions);
            Elements es=doc.select(".pagination").get(0).getElementsByTag("a");
            doc=null;
            for(Element href : es){
                if(href.text().equals(">")){
                    url="http://www.jiankang.com"+href.attr("href");
                    doc=JsoupUtil.GetDocument(url);
                }
            }
        }
    }
}
