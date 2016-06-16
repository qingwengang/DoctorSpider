package Spider.Bll.JK;

import Spider.Config.StockConfig;
import Spider.DO.JK.JKQuestionDO;
import Spider.Dao.BaseDao;
import Spider.Dao.JKQuestionDao;
import Spider.Entity.JKQuestion;
import SpiderFramework.Bll.BaseHandlerThread;
import SpiderFramework.Bll.SpiderHandler;
import Util.FileHelper;
import Util.JsoupUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.annotations.SourceType;
import org.jsoup.Jsoup;
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
        String url=MessageFormat.format("http://www.jiankang.com/ask/{0}.shtml", Long.toString(jkQuestion.getOutId()));
        Document doc= JsoupUtil.GetDocument(url);
        String html=doc.html().replaceAll("(?i)<br[^>]*>", "卿文刚");
        doc= Jsoup.parse(html);
        Element questionArea=doc.select(".question_area").get(0);
        Element spanDate=questionArea.select(".date").get(0);
        Element h1=questionArea.getElementsByTag("h1").get(0);
        Element h2=questionArea.getElementsByTag("h2").get(0);
        Elements descDls=questionArea.select(".disease_dl");
        Elements answerDivs=doc.select(".diease_analysis");
        String title=h1.text();
        String sex="";
        String age="";
        String address="";
        String questionDesc="";
        List<String> answers=new LinkedList<>();
        StringBuffer sbDesc=new StringBuffer();
        for(Element e : h2.children()){
            if(e.attr("itemprop").equals("sex")){
                sex=e.text();
            }
            if(e.attr("itemprop").equals("age")){
                age=e.text();
            }
            if(e.attr("itemprop").equals("address")){
                address=e.text();
            }
        }
        for(Element dl : descDls){
            for(Element e : dl.children()){
                sbDesc.append(e.text()).append("卿文刚");
            }
        }
        questionDesc=sbDesc.toString();
        for(Element answer : answerDivs){
            StringBuffer sbAnswer=new StringBuffer();
            for(Element e : answer.children()){
                if(e.text()!=null && e.text().length()>0){
                    sbAnswer.append(e.text()).append("卿文刚");
                }
            }
            answers.add(sbAnswer.toString());
        }
        JKQuestionDO questionDo=new JKQuestionDO(title,sex,age,address,questionDesc,answers);
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(questionDo);
        } catch (IOException e) {
        }
        if(json!=null && !json.equals("")){
            String path= StockConfig.JKPath+jkQuestion.getTypeName();
            try {
                new FileHelper().Write(path,Long.toString(jkQuestion.getOutId()),json);
            } catch (IOException e) {
                throw e;
            }
        }
//        System.out.println(title);
//        System.out.println(sex);
//        System.out.println(age);
//        System.out.println(address);
//        System.out.println(questionDesc);
//        System.out.println(answers);
//        System.out.println(spanDate);
//        for(String an : answers){
//            System.out.println(an);
//        }
    }
}
