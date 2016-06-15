package Spider.Bll.jiujiu;

import Spider.Config.StockConfig;
import Spider.DO.Jiujiu.JjQuestionDo;
import Spider.Dao.JjQuestionDao;
import Spider.Entity.JjQuestion;
import SpiderFramework.Bll.SpiderHandler;
import Util.FileHelper;
import Util.JsoupUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

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
        String url="http://ask.9939.com/id/"+jjQuestion.getOutId();
        Document doc= JsoupUtil.GetDocument(url);
        String html=doc.html().replaceAll("(?i)<br[^>]*>", "卿文刚");
        doc=Jsoup.parse(html);
        Elements es=doc.select(".a_bol");
        String typeName="";
        String title="";
        String desc="";
        String authorDesc="";
        for(Element e : es){
            if(e.attr("href").contains("disease")){
                typeName=e.text();
            }
        }
        title=doc.select(".mebtit").get(0).getElementsByTag("h3").get(0).text();
        authorDesc=doc.select(".mebnews").get(0).text();
        Element ele=doc.select(".ldesc").get(0);
        for(Element e : ele.children()){
            if(e.attr("class").contains("quebox")){
                break;
            }else {
                desc+="卿文刚"+e.text();
            }
        }
        List<String> answers=new LinkedList<>();
        Elements answerDivs=doc.select(".bacore");
        for(Element answerDiv : answerDivs){
            for(Element answer:answerDiv.children()){
                answers.add(answer.text()+"卿文刚");
            }
        }
        JjQuestionDo questionDo=new JjQuestionDo(typeName,title,desc,Long.toString(jjQuestion.getOutId()),answers);
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(questionDo);
        } catch (IOException e) {
        }
        if(json!=null && !json.equals("")){
            String path= StockConfig.JiujiuPath+questionDo.getTypeName();
            try {
                new FileHelper().Write(path,questionDo.getOutId(),json);
            } catch (IOException e) {
                throw e;
            }
        }
//        System.out.println(typeName);
//        System.out.println(title);
//        System.out.println(authorDesc);
//        System.out.println(desc);
//        System.out.println(answers);
    }
}
