package Spider.Bll;

import Spider.Config.StockConfig;
import Spider.DO.AnswerDo;
import Spider.DO.QuestionDo;
import Spider.Dao.AskQuestionDao;
import Spider.Dao.DoctorInfoDao;
import Spider.Entity.AskQuestion;
import Spider.Entity.DoctorInfo;
import SpiderFramework.Bll.SpiderHandler;
import Util.BTreeUtil;
import Util.FileHelper;
import Util.FormatUtil;
import Util.JsoupUtil;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/6/3.
 */
public class SpiderAskQuestionHandler2 extends SpiderHandler<AskQuestion,AskQuestion> {
    private static Logger logger = Logger.getLogger(SpiderAskQuestionHandler2.class);
    private AskQuestionDao questionDao=new AskQuestionDao();
    public SpiderAskQuestionHandler2() {
        super("获取120ask问题内容",1,0);
    }
    public SpiderAskQuestionHandler2(int handlerCount, int handlerNo){
        super("获取120ask问题内容",handlerCount,handlerNo);
    }

    @Override
    public List<AskQuestion> getUnspiderData() {
        String sql= MessageFormat.format("select * from askquestion where SpiderFlag=0  and id%{0}={1} LIMIT 0,10",getThreadCount(),getThreadNo());
        List<AskQuestion> questions=questionDao.Query(sql);
        return questions;
    }

    @Override
    public void SpiderBll(AskQuestion askQuestion) throws IOException {
        String url="http://www.120ask.com/question/"+askQuestion.getOutId()+".htm";
        Document doc = JsoupUtil.GetDocument(url);
        Element daohang=doc.select(".b_route").get(0);
        Elements daohangHrefs=daohang.getElementsByTag("a");
        for(Element href : daohangHrefs){
            if(href.attr("itemprop").equals("link")){
                String type=href.attr("href");
                String[] ts=type.split("/");
                askQuestion.setType(ts[4]);
            }
        }
        Element h1Title=doc.select("#d_askH1").get(0);
        QuestionDo questionDo=new QuestionDo();
        questionDo.setId(askQuestion.getId());
        questionDo.setTitle(h1Title.text());
        questionDo.setOutId(askQuestion.getOutId());
        questionDo.setType(askQuestion.getType());
        questionDo.setId(askQuestion.getId());
        Elements spans=doc.select(".b_askab1").get(0).children();
        String sex="";
        String laiyuan="";
        String sj="";
        try {
            for (Element span : spans) {
                if (span.text().contains("|")) {
                    sex = span.text().trim();
                } else if (span.text().contains("来自")) {
                    laiyuan = span.text().trim();
                } else if (span.text().contains("人回复")) {

                } else {
                    sj = span.text().trim();
                }
            }
        }catch (Exception e){
            logger.error("120ask 步骤1错误!" + askQuestion.getId(), e);
            throw e;
        }
        questionDo.setAuthSex(sex);
        questionDo.setLaiyuan(laiyuan);
        try{
            askQuestion.setAuthorTime(FormatUtil.GetDateTimeByString(sj));
        }catch (Exception e){
//            logger.error("question.setAuthorTime(FormatUtil.GetDateTimeByString(sj));"+question.getId(), e);
        }


        try{
            Element divContent=doc.select("#d_msCon").get(0);
            for(Element e : divContent.children()){
                if(e.text().startsWith("健康咨询描述")){
                    questionDo.setContent(e.text());
                }
                if(e.text().startsWith("想")){
                    questionDo.setNeedHelp(e.text());
                }
            }
            String askauthortext="";
            Element askAuthor = doc.select(".ask_Author").get(0);
            askauthortext=askAuthor.text();
            questionDo.setAuthorId(askauthortext);}catch (Exception e){
            logger.error("120ask 步骤2错误!" + askQuestion.getId(), e);
        }
        //回答区
        Elements answerDivs=doc.select(".crazy_new");
        for(Element answerDiv : answerDivs){
            AnswerDo answerDo=new AnswerDo();
            answerDo.setContent(answerDiv.text());
            questionDo.getAnswers().add(answerDo);
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(questionDo);
        } catch (IOException e) {
            logger.error("json serial exception!",e);
        }
        if(json!=null){
            String path= StockConfig.askQuestionPath+questionDo.getType()+"/"+questionDo.getOutId().substring(0,1)+"/"+questionDo.getOutId().substring(1,2)+"/";
            try {
                new FileHelper().Write(path,questionDo.getOutId(),json);
            } catch (IOException e) {
                logger.error("json into file exception!", e);
                throw e;
            }
        }
        //end 回答区
    }

}
