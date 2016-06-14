package Spider.Bll.jiujiu;

import Spider.Dao.JjQuestionDao;
import Spider.Entity.JjQuestion;
import SpiderFramework.Bll.SpiderHandler;
import Util.JsoupUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.MessageFormat;

/**
 * Created by Administrator on 2016/6/13.
 */
public class SpiderJiujiuQuestionHandler extends SpiderHandler<JjQuestion,JjQuestion> {

    public SpiderJiujiuQuestionHandler() {
        super("获取99问题信息",0,1);
        setGetUnspiderDataSql(MessageFormat.format("select * from jjquestion where SpiderFlag=0  and id%{0}={1} LIMIT 0,10", 1, 0));
    }

    public SpiderJiujiuQuestionHandler( int threadCount, int threadNo) {
        super("获取99问题信息", threadCount, threadNo,new JjQuestionDao(),new JjQuestionDao());
        setGetUnspiderDataSql(MessageFormat.format("select * from jjquestion where SpiderFlag=0  and id%{0}={1} LIMIT 0,10", threadCount, threadNo));
    }

    @Override
    public void SpiderBll(JjQuestion jjQuestion) throws IOException {
        String url="http://ask.9939.com/id/"+4000000;
        Document doc= JsoupUtil.GetDocument(url);
        String html=doc.html().replaceAll("(?i)<br[^>]*>", "卿文刚");
        doc=Jsoup.parse(html);
        System.out.print(doc.html());
        Elements es=doc.select(".a_bol");
        String typeName="";
        for(Element e : es){
            if(e.attr("href").contains("disease")){
                typeName=e.text();
            }
        }
        System.out.print(doc.select(".ldesc").get(0).text());
        Element ele=doc.select(".ldesc").get(0);

        for(Element e : ele.children()){
            if(e.attr("class").contains("quebox")){

            }
        }
//        bianli(doc.select(".ldesc").get(0));
    }
    private void bianli(Element e){
        if(e.children()!=null &&e.children().size()>0){
            for(Element es : e.children()){
                bianli(es);
            }
        }else if(e.text()!=null && e.text().length()>0){
            System.out.print(e.text());
        }
    }
}
