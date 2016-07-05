package Spider.Bll.JKW;

import Spider.Config.StockConfig;
import Spider.DO.JKW.JKWQuestionDO;
import Spider.DO.Online.OnlineQuestionDo;
import Spider.Dao.BaseDao;
import Spider.Dao.JKWQuestionDao;
import Spider.Entity.JKWQuestion;
import SpiderFramework.Bll.SpiderHandler;
import Util.FileHelper;
import Util.FormatUtil;
import Util.JsonUtil;
import Util.JsoupUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/17.
 */
public class SpiderJKWQuestionHandler extends SpiderHandler<JKWQuestion,JKWQuestion> {
    private JsonUtil jsonUtil=new JsonUtil();
    private JKWQuestionDao questionDao=new JKWQuestionDao();
    public SpiderJKWQuestionHandler() {
        super("获取jkw的问题信息");
        setGetUnspiderDataSql(MessageFormat.format("select * from jkwquestion where SpiderFlag=0  and id%{0}={1} LIMIT 0,10", 1, 0));
    }

    public SpiderJKWQuestionHandler( int threadCount, int threadNo) {
        super("获取jkw的问题信息", threadCount, threadNo, new JKWQuestionDao(),new JKWQuestionDao());
        setGetUnspiderDataSql(MessageFormat.format("select * from jkwquestion where SpiderFlag=0  and id%{0}={1} LIMIT 0,10", threadCount, threadNo));
    }

    @Override
    public void SpiderBll(JKWQuestion jkwQuestion) throws IOException {
        String url=MessageFormat.format("http://www.jianke.com/ask/question/{0}", jkwQuestion.getOutId());
        Document doc= JsoupUtil.GetDocument(url);
        String html=doc.html().replaceAll("(?i)<br[^>]*>", "卿文刚");
        doc= Jsoup.parse(html);
        Element h5=doc.select("h5").get(0);
        Elements hrefs=h5.select("a");
        String typeName=hrefs.get(hrefs.size() - 1).text();
        jkwQuestion.setTypeName(typeName);
        String title=doc.select(".why > h1").get(0).text();
        String sDate=doc.select(".why > i").get(0).text();
        try {
            jkwQuestion.setPublishTime(FormatUtil.GetDateByString(sDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String sexage=doc.select(".name_age").get(0).text();
        Elements elementsDesc=doc.select(".pd_txt");
        String sDesc="";
        if(elementsDesc!=null && elementsDesc.size()>0){
            sDesc=elementsDesc.get(0).text();
        }
        Elements answerDivs=doc.select(".an_cont");
        List<String> answer=new LinkedList<>();
        for(Element answerDiv : answerDivs){
            String answerString="";
            for(Element dl : answerDivs.select("dl")){
                for(Element dt : dl.select("dt")){
                    answerString+=dt.text()+"卿文刚";
                }
                for(Element dt : dl.select("dd")){
                    answerString+=dt.text()+"卿文刚";
                }
            }
            answer.add(answerString);
        }
        JKWQuestionDO questionDO=new JKWQuestionDO(title,typeName,sDesc,sexage,answer);
        jsonUtil.WriteJsonToFileFromObj(questionDO, StockConfig.JKWPath+questionDO.getTypeName()+"/",jkwQuestion.getOutId());
    }

    @Override
    public List<OnlineQuestionDo> CreateQuestionImpl(String where, int getCount) {
        List<OnlineQuestionDo> dos=new LinkedList<>();
        String sqlTemp="select {0} from jkwquestion where SpiderFlag=1 and typename in({1}) and (IfCreated is null or IfCreated!=1) {2}";
        String sql=MessageFormat.format(sqlTemp,"count(1)",where,"");
        int count=questionDao.GetOneColumn(sql);
        List<JKWQuestion> questionList=new LinkedList<>();
        if(count<=getCount){
            String getQuestionSql=MessageFormat.format(sqlTemp,"*",where,"");
            questionList=questionDao.Query(getQuestionSql);
        }else{
            int pageSize=count/getCount;
            for(int i=1;i<=getCount;i++){
                String getQuestionSql=MessageFormat.format(sqlTemp,"*",where,MessageFormat.format("limit {0},1",i*pageSize-1));
                JKWQuestion question=questionDao.QuerySingle(getQuestionSql);
                questionList.add(question);
            }
        }
        for(JKWQuestion question : questionList){
            String path= StockConfig.JKWPath+question.getTypeName()+"/";
            JKWQuestionDO questionDo=null;
            try {
                String json=new FileHelper().Read(path,question.getOutId());
                ObjectMapper mapper = new ObjectMapper();
                questionDo=mapper.readValue(json, JKWQuestionDO.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(questionDo!=null){
                OnlineQuestionDo onlineQuestionDo=new OnlineQuestionDo();
                onlineQuestionDo.setSource("jkw");
                onlineQuestionDo.setOutId(question.getOutId());
                onlineQuestionDo.setTitle(questionDo.getTitle());
                onlineQuestionDo.setContent(questionDo.getDesc());
                for(String answer : questionDo.getAnswers()){
                    onlineQuestionDo.getAnswers().add(answer);
                }
                onlineQuestionDo.setJkwQuestion(question);
                dos.add(onlineQuestionDo);
            }
        }
        return dos;
    }
}
