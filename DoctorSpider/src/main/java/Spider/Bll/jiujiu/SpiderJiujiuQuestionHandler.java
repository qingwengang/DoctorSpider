package Spider.Bll.jiujiu;

import Spider.Config.StockConfig;
import Spider.DO.Jiujiu.JjQuestionDo;
import Spider.DO.Online.OnlineQuestionDo;
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
    private JjQuestionDao questionDao=new JjQuestionDao();
    public SpiderJiujiuQuestionHandler() {
        super("获取99问题信息",0,1,new JjQuestionDao(),new JjQuestionDao());
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
        jjQuestion.setTypeName(typeName);
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
            StringBuilder sbAnswer=new StringBuilder();
            for(Element answer:answerDiv.children()){
                sbAnswer.append(answer.text()).append("卿文刚");
            }
            answers.add(sbAnswer.toString());
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
    }
    @Override
    public List<OnlineQuestionDo> CreateQuestionImpl(String where, int getCount) {
        List<OnlineQuestionDo> dos=new LinkedList<>();
        String sqlTemp="select {0} from jjquestion where SpiderFlag=1 and typename in({1}) and (IfCreated is null or IfCreated!=1) {2}";
        String sql=MessageFormat.format(sqlTemp,"count(1)",where,"");
        int count=questionDao.GetOneColumn(sql);
        List<JjQuestion> questionList=new LinkedList<>();
        if(count<=getCount){
            String getQuestionSql=MessageFormat.format(sqlTemp,"*",where,"");
            questionList=questionDao.Query(getQuestionSql);
        }else{
            int pageSize=count/getCount;
            for(int i=1;i<=getCount;i++){
                String getQuestionSql=MessageFormat.format(sqlTemp,"*",where,MessageFormat.format("limit {0},1",i*pageSize-1));
                JjQuestion question=questionDao.QuerySingle(getQuestionSql);
                questionList.add(question);
            }
        }
        for(JjQuestion question : questionList){
            String path= StockConfig.JiujiuPath+question.getTypeName()+"/";
            JjQuestionDo questionDo=null;
            try {
                String json=new FileHelper().Read(path,Long.toString(question.getOutId()));
                ObjectMapper mapper = new ObjectMapper();
                questionDo=mapper.readValue(json, JjQuestionDo.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(questionDo!=null){
                OnlineQuestionDo onlineQuestionDo=new OnlineQuestionDo();
                onlineQuestionDo.setSource("jj");
                onlineQuestionDo.setOutId(Long.toString(question.getOutId()));
                onlineQuestionDo.setTitle(questionDo.getTitle());
                onlineQuestionDo.setContent(questionDo.getDesc());
                for(String answer : questionDo.getAnswers()){
                    onlineQuestionDo.getAnswers().add(answer);
                }
                onlineQuestionDo.setJjQuestion(question);
                dos.add(onlineQuestionDo);
            }
        }
        return dos;
    }
}
