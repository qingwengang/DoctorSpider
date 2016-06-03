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
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/6/3.
 */
public class SpiderAskQuestionHandler  extends SpiderHandler<AskQuestion> {
    private static Logger logger = Logger.getLogger(SpiderAskQuestionHandler.class);
    private AskQuestionDao questionDao=new AskQuestionDao();
    public SpiderAskQuestionHandler() {
        super("获取120ask问题内容");
    }

    @Override
    public List<AskQuestion> getUnspiderData() {
        String sql="select * from askquestion where SpiderFlag=0 LIMIT 0,10";
        List<AskQuestion> questions=questionDao.Query(sql);
        return questions;
    }

    @Override
    public void SpiderBll(AskQuestion askQuestion) {
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
        }
        questionDo.setAuthSex(sex);
        questionDo.setLaiyuan(laiyuan);
        try{
            askQuestion.setAuthorTime(FormatUtil.GetDateTimeByString(sj));
        }catch (Exception e){
//            logger.error("question.setAuthorTime(FormatUtil.GetDateTimeByString(sj));"+question.getId(), e);
        }

//        spans.forEach(x-> System.out.println(x.text()));
        try{
            Element divContent=doc.select("#d_msCon").get(0);
            for(Element e : divContent.children()){
                if(e.text().startsWith("健康咨询描述")){
                    questionDo.setContent(e.text());
                }
                if(e.text().startsWith("想得到的帮助：")){
                    questionDo.setNeedHelp(e.text());
                }
            }
            String askauthortext="";
            try {
                Element askAuthor = doc.select(".ask_Author").get(0);
                askauthortext=askAuthor.text();
            }catch (Exception e){

            }
            questionDo.setAuthorId(askauthortext);}catch (Exception e){
            logger.error("120ask 步骤2错误!" + askQuestion.getId(), e);
        }
        //回答区
        Elements answerDivs=doc.select(".b_answerli");
        for(Element authDiv : answerDivs){
            if(authDiv.select(".b_sp1").size()>0){
                String docName=authDiv.select(".b_sp1").get(0).getElementsByTag("a").get(0).text();
                String docUrl=authDiv.select(".b_sp1").get(0).getElementsByTag("a").get(0).attr("href");
                String title=authDiv.select(".b_sp1").get(0).text().replace(docName,"");
//                System.out.print(docName);
                Elements sp2s=authDiv.select(".b_sp2");
                String expertise="";
                for(Element sp2 : sp2s){
                    if(sp2.text().startsWith("擅长:")){
                        expertise=sp2.text().substring(3);
                    }
                }
                String answerContent=authDiv.select(".b_anscont_cont").text();
                Element eDate=authDiv.select(".b_anscont_time").get(0);
                Date answerTime=null;
                try{
                    answerTime= FormatUtil.GetDateTimeByString(eDate.text());
                }catch (Exception e){

                }
                System.out.println(eDate.text());
                try{
                    DoctorInfo doctorInfo=new DoctorInfo();
                    doctorInfo.setName(docName);
                    doctorInfo.setTitle(title);
                    doctorInfo.setExpertise(expertise);
                    if(!docUrl.contains("doctor")){
                        doctorInfo.setDocId(docUrl.split("/")[5]);
                    }else{
                        doctorInfo.setType(1);
                        doctorInfo.setDocId(docUrl.split("/")[4]);
                    }
                    if(!BTreeUtil.CheckIfExist("docId", doctorInfo.getDocId())){
                        new DoctorInfoDao().Add(doctorInfo);
                        BTreeUtil.AddValue("docId",doctorInfo.getDocId());
                    }
                    AnswerDo answer=new AnswerDo();
                    answer.setDoctorId(doctorInfo.getDocId());
                    answer.setContent(answerContent);
                    answer.setAnswerTime(answerTime);
                    boolean ifExist=false;
                    for(AnswerDo answerDo : questionDo.getAnswers()){
                        if(answerDo.getDoctorId().equals(answer.getDoctorId())&& answerDo.getAnswerTime().equals(answer.getAnswerTime())){
                            ifExist=true;
                        }
                    }
                    if(!ifExist){
                        questionDo.getAnswers().add(answer);
                    }
                }catch (Exception e){
                    logger.error("add doctorinfo error!" + askQuestion.getId() + "|" + docUrl, e);
                }

            }
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
            }
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!"+json);
        //end 回答区
    }

    @Override
    public void Update(AskQuestion askQuestion) {
        questionDao.Update(askQuestion);
    }
}
