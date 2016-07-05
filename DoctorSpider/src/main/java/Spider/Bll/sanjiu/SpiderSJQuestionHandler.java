package Spider.Bll.sanjiu;

import Spider.Config.StockConfig;
import Spider.DO.Online.OnlineQuestionDo;
import Spider.DO.SJ.SJQuestionDO;
import Spider.Dao.BaseDao;
import Spider.Dao.SJQuestionDao;
import Spider.Entity.SJQuestion;
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
 * Created by Administrator on 2016/6/17.
 */
public class SpiderSJQuestionHandler extends SpiderHandler<SJQuestion,SJQuestion> {
    private SJQuestionDao questionDao=new SJQuestionDao();

    public SpiderSJQuestionHandler() {
        super("获取39问题信息", 1, 0, new SJQuestionDao() , new SJQuestionDao());
        setGetUnspiderDataSql(MessageFormat.format("select * from sjquestion where SpiderFlag=0  and id%{0}={1} LIMIT 0,10", 1, 0));
    }

    public SpiderSJQuestionHandler( int threadCount, int threadNo) {
        super("获取39问题信息", threadCount, threadNo, new SJQuestionDao() , new SJQuestionDao());
        setGetUnspiderDataSql(MessageFormat.format("select * from sjquestion where SpiderFlag=0  and id%{0}={1} LIMIT 0,10", threadCount, threadNo));
    }

    @Override
    public void SpiderBll(SJQuestion sjQuestion) throws IOException {
        String url=MessageFormat.format("http://ask.39.net/question/{0}.html",sjQuestion.getOutId());
        Document doc= JsoupUtil.GetDocument(url);
        String html=doc.html().replaceAll("(?i)<br[^>]*>", "卿文刚");
        doc= Jsoup.parse(html);
        Elements links=doc.select(".sub > span > a");
        String typeName=links.get(links.size()-1).text();
//        System.out.println(typeName);
        Element descDiv=doc.select(".tbox_nobg").get(0).select(".t_right").get(0);
        String title=descDiv.select("h1").get(0).text();
        Elements lis=descDiv.select("ul.user_msg > li");
        String desc="";
        for(Element li : lis){
            desc+=li.text()+"卿文刚";
        }
        Elements ps=descDiv.select(".user_p");
        for(Element e : ps){
            desc+=e.text()+"卿文刚";
        }
        Element answersDiv = doc.select("#doctor_reply").get(0);
        List<String> answerlist=new LinkedList<String>();
        if(answersDiv!=null){
            Elements answers=answersDiv.select(".t_right");
            for(Element answer : answers){
                String answerString="";
                Elements answerps=answer.select(".user_p");
                for(Element answerp : answerps){
                    answerString+=answerp.text()+"卿文刚";
                }
                Elements talklis=answersDiv.select(".user_talk > li");
                for(Element li : talklis){
                    Element i=li.select("i").get(0);
                    answerString+=i.text();
                    Elements ansps=li.select("p");
                    for(Element ansp : ansps){
                        answerString+=ansp.text()+"卿文刚";
                    }
                }
                answerlist.add(answerString);
            }
        }
        sjQuestion.setTypeName(typeName);
        SJQuestionDO questionDo=new SJQuestionDO(title,typeName,desc,answerlist);
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(questionDo);
        } catch (IOException e) {
        }
        if(json!=null && !json.equals("")){
            String path= StockConfig.SJPath+questionDo.getTypeName();
            try {
                new FileHelper().Write(path,sjQuestion.getOutId(),json);
                sjQuestion.setIfWriteFile(1);
            } catch (IOException e) {
                throw e;
            }
        }
    }

    @Override
    public List<OnlineQuestionDo> CreateQuestionImpl(String where, int getCount) {
        List<OnlineQuestionDo> dos=new LinkedList<>();
        String sqlTemp="select {0} from sjquestion where SpiderFlag=1 and typename in({1}) and (IfCreated is null or IfCreated!=1) {2}";
        String sql=MessageFormat.format(sqlTemp,"count(1)",where,"");
        int count=questionDao.GetOneColumn(sql);
        List<SJQuestion> questionList=new LinkedList<>();
        if(count<=getCount){
            String getQuestionSql=MessageFormat.format(sqlTemp,"*",where,"");
            questionList=questionDao.Query(getQuestionSql);
        }else{
            int pageSize=count/getCount;
            for(int i=1;i<=getCount;i++){
                String getQuestionSql=MessageFormat.format(sqlTemp,"*",where,MessageFormat.format("limit {0},1",i*pageSize-1));
                SJQuestion question=questionDao.QuerySingle(getQuestionSql);
                questionList.add(question);
            }
        }
        for(SJQuestion question : questionList){
            String path= StockConfig.SJPath+question.getTypeName()+"/";
            SJQuestionDO questionDo =null;
            try {
                String json=new FileHelper().Read(path,question.getOutId());
                ObjectMapper mapper = new ObjectMapper();
                questionDo=mapper.readValue(json, SJQuestionDO.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(questionDo!=null){
                OnlineQuestionDo onlineQuestionDo=new OnlineQuestionDo();
                onlineQuestionDo.setSource("sj");
                onlineQuestionDo.setOutId(question.getOutId());
                onlineQuestionDo.setTitle(questionDo.getTitle());
                onlineQuestionDo.setContent(questionDo.getDesc());
                for(String answer : questionDo.getAnswers()){
                    onlineQuestionDo.getAnswers().add(answer);
                }
                onlineQuestionDo.setSjQuestion(question);
                dos.add(onlineQuestionDo);
            }
        }
        return dos;
    }
}
