package Spider.Bll;

import Spider.Config.StockConfig;
import Spider.DO.FH.FHQuestionDo;
import Spider.Dao.FHQuesionDao;
import Spider.Entity.FHQuestion;
import SpiderFramework.Bll.SpiderHandler;
import SpiderFramework.Entity.BaseSpiderEntity;
import Util.FileHelper;
import Util.JsoupUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/6/8.
 */
public class SpiderFHQuestion extends SpiderHandler<FHQuestion,FHQuestion> {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SpiderFHQuestion.class);
    public static void main(String[] args){
        Spider(102785561);
    }
    private FHQuesionDao quesionDao=new FHQuesionDao();
    public SpiderFHQuestion() {
        super("获取HF问题内容",1,0,new FHQuesionDao(),new FHQuesionDao());
    }
    public SpiderFHQuestion(int handlerCount,int handlerNo){
        super("获取HF问题内容",handlerCount,handlerNo,new FHQuesionDao(),new FHQuesionDao());
    }
    private static void Spider(long id){
        String url="http://iask.fh21.com.cn/question/"+id+".html";
        Document doc = JsoupUtil.GetDocument(url);
        System.out.print(doc.select(".iask_navigator").get(0).text());
        String title=doc.select(".iask_detail01a").get(0).getElementsByTag("ul").get(0).text();
        String questionInfo=doc.select(".iask_detail01a").get(0).getElementsByTag("ol").get(0).text();
        System.out.println(title);
        System.out.println(questionInfo);
        Elements uls=doc.select(".iask_detail01b1").get(0).children();
        String des="";
        for(Element ul : uls){
            des+=ul.text()+"|";
        }
        System.out.println(des);
        Elements answers=doc.select(".iask_answer02a");
        for(Element answer : answers){
            System.out.println(answer.text());
        }

    }

    @Override
    public List getUnspiderData() {
        String sql= MessageFormat.format("select * from fhquestion where SpiderFlag=0  and id%{0}={1} LIMIT 0,10", getThreadCount(), getThreadNo());
        List<FHQuestion> questions=quesionDao.Query(sql);
        return questions;
    }

    @Override
    public void SpiderBll(FHQuestion que) throws IOException {
        try {
            FHQuestionDo questionDo=new FHQuestionDo();
            String url = "http://iask.fh21.com.cn/question/" + que.getOutId() + ".html";
            Document doc = JsoupUtil.GetDocument(url);
            String tags=doc.select(".iask_navigator").get(0).text();
            String[] tagArray=tags.split(">");
            String title = doc.select(".iask_detail01a").get(0).getElementsByTag("ul").get(0).text();
            String questionInfo = doc.select(".iask_detail01a").get(0).getElementsByTag("ol").get(0).text();
            Elements uls = doc.select(".iask_detail01b1").get(0).children();
            String des = "";
            for (Element ul : uls) {
                des += ul.text() + "|";
            }
            questionDo.setOutId(Long.parseLong(que.getOutId()));
            questionDo.setDesc(des);
            questionDo.setTitle(title);
            questionDo.setQuestionInfo(questionInfo);
            Elements answers = doc.select(".iask_answer02a");
            for (Element answer : answers) {
                questionDo.getAnswers().add(answer.text());
            }

            ObjectMapper mapper = new ObjectMapper();
            String json = "";
            try {
                json = mapper.writeValueAsString(questionDo);
            } catch (IOException e) {
                logger.error("json serial exception!",e);
            }
            if(json!=null){
                String path= StockConfig.FHQuestionPath;
                String muluName=tagArray[tagArray.length-1];
                for(String tag : tagArray){
                    path+=tag.trim()+"/";
                }
                try {
                    new FileHelper().Write(path,que.getOutId(),json);
                    que.setWriteFileFlag(1);
                    que.setMuluName(muluName);
//                    quesionDao.Update(que);
                } catch (IOException e) {
                    logger.error("json into file exception!", e);
                    throw e;
                }
            }
        }catch (Exception e){
            logger.error("获取飞华问题数据异常,outid:"+que.getOutId(),e);
        }
    }

}
