package Spider.Bll.jiujiu;

import Spider.Dao.BaseDao;
import Spider.Dao.JjQuestionDao;
import Spider.Dao.MuluDao;
import Spider.Entity.JjQuestion;
import Spider.Entity.Mulu;
import SpiderFramework.Bll.SpiderHandler;
import SpiderFramework.Entity.BaseSpiderEntity;
import Util.JsoupUtil;
import javafx.collections.transformation.SortedList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/15.
 */
public class GetJiujiuQuestionListHandler extends SpiderHandler<Mulu,JjQuestion> {
    private MuluDao muluDao=new MuluDao();
    private JjQuestionDao questionDao=new JjQuestionDao();

    public GetJiujiuQuestionListHandler() {
        super("获取9939问题列表",1,0);
    }

    public GetJiujiuQuestionListHandler( int threadCount, int threadNo) {
        super("获取9939问题列表", threadCount, threadNo,new MuluDao(),new JjQuestionDao());
        setGetUnspiderDataSql(MessageFormat.format("select * from mulu where source='9939' and level=3 and SpiderFlag=0 and id%{0}={1} limit 0,10",threadCount,threadNo));
    }


    @Override
    public void SpiderBll(Mulu mulu) throws IOException {
        String url="http://ask.9939.com"+mulu.getUrl();
        Document doc= JsoupUtil.GetDocument(url);
        LinkedList<Long> outIds=new LinkedList<Long>();
        while(doc!=null){
            Elements divs=doc.select(".lpom");
            List<JjQuestion> toAddQuestions=new LinkedList<>();
            for(Element div : divs){
                Elements links=div.getElementsByTag("a");
                for(Element link : links){
                    if(link.attr("href").startsWith("/id")){
                        long outid=Long.parseLong(link.attr("href").substring(4));
                        if(!outIds.contains(outid)){
                            outIds.add(outid);
                            JjQuestion question=new JjQuestion();
                            question.setOutId(outid);
                            toAddQuestions.add(question);
                        }
                    }
                }
            }
            if(toAddQuestions.size()>0){
                questionDao.Add(toAddQuestions);
            }
            Elements es=doc.select(".lpage_a");
            doc=null;
            for(Element e : es){
                if(e.text().equals("下一页>>")){
                    url="http://ask.9939.com"+e.attr("href");
                    doc=JsoupUtil.GetDocument(url);
                }
            }
        }
    }
}
