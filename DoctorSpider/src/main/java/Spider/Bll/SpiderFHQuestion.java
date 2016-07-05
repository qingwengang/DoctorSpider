package Spider.Bll;

import Spider.Config.StockConfig;
import Spider.DO.FH.FHQuestionDo;
import Spider.DO.Online.OnlineQuestionDo;
import Spider.Dao.FHQuesionDao;
import Spider.Entity.AskQuestion;
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
import java.util.LinkedList;
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
                String muluName=tagArray[tagArray.length-1].trim();
                path+=muluName+"/";
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

    @Override
    public List<OnlineQuestionDo> CreateQuestionImpl(String where, int getCount) {
        List<OnlineQuestionDo> dos=new LinkedList<>();
        String sql=MessageFormat.format("select count(1) from fhquestion where WriteFileFlag=1 and MuluName in ({0}) and (ifcreated is null or ifcreated!=1)",where);
        int count=quesionDao.GetOneColumn(sql);
        List<FHQuestion> questionList=new LinkedList<>();
        if(count<=getCount){
            String getQuestionSql=MessageFormat.format("select * from fhquestion where WriteFileFlag=1 and muluname in ({0}) and (ifcreated is null or ifcreated!=1)", where);
            questionList=quesionDao.Query(getQuestionSql);
        }else{
            int pageSize=count/getCount;
            for(int i=1;i<=getCount;i++){
                String getQuestionSql=MessageFormat.format("select * from fhquestion where WriteFileFlag=1 and muluname in({0})  and (ifcreated is null or ifcreated!=1) LIMIT {1},1",where,i*pageSize-1);
                FHQuestion question=quesionDao.QuerySingle(getQuestionSql);
                questionList.add(question);
            }
        }
        for(FHQuestion question : questionList){
            String path=StockConfig.FHQuestionPath+question.getMuluName()+"/";
            FHQuestionDo questionDo=null;
            try {
                String json=new FileHelper().Read(path,question.getOutId());
                ObjectMapper mapper = new ObjectMapper();
                questionDo=mapper.readValue(json, FHQuestionDo.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(questionDo!=null) {
                OnlineQuestionDo onlineQuestionDo=new OnlineQuestionDo();
                onlineQuestionDo.setTitle(questionDo.getTitle());
                onlineQuestionDo.setContent(questionDo.getQuestionInfo());
                onlineQuestionDo.setDesc(questionDo.getDesc());
                for(String answer : questionDo.getAnswers()){
                    onlineQuestionDo.getAnswers().add(answer);
                }
                onlineQuestionDo.setFhQuestion(question);
                dos.add(onlineQuestionDo);
            }
        }
        return dos;
    }
}
