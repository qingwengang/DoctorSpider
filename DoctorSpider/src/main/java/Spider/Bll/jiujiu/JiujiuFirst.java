package Spider.Bll.jiujiu;

import Spider.Dao.BaseDao;
import Spider.Dao.JjQuestionDao;
import Spider.Dao.MuluDao;
import Spider.Entity.JjQuestion;
import Spider.Entity.Mulu;
import Util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/13.
 */
public class JiujiuFirst {
    private static BaseDao<Mulu> dao=new MuluDao();
    private static BaseDao<JjQuestion> jjDao=new JjQuestionDao();
    public static void  main(String[] args){
//        getFirstlevelMulu();
//        getTwoLewvelMulu();
//        getThredLewvelMulu();
        initQuestion();
    }

    public static void getFirstlevelMulu(){
        String url="http://ask.9939.com/classid/33";
        Document doc = JsoupUtil.GetDocument(url);
        Elements es=doc.select(".ksul > li > a");
        for(Element e : es){
            Mulu mulu=new Mulu();
            mulu.setName(e.text());
            mulu.setUrl(e.attr("href"));
            mulu.setSource("9939");
            mulu.setLevel(1);
            mulu.setSpiderFlag(0);
            dao.Add(mulu);
        }
    }
    public static void getTwoLewvelMulu() {
        String url = "http://ask.9939.com/classid/33";
        Document doc = JsoupUtil.GetDocument(url);
        Elements es = doc.select(".mebox_a > a");
        for (Element e : es) {
            Mulu mulu1 = new Mulu();
            mulu1.setName(e.text());
            mulu1.setUrl(e.attr("href"));
            mulu1.setSource("9939");
            mulu1.setLevel(2);
            mulu1.setSpiderFlag(0);
            dao.Add(mulu1);

        }
    }
    public static void getThredLewvelMulu(){
        String url = "http://ask.9939.com/classid/33";
        Document doc = JsoupUtil.GetDocument(url);
        Elements es = doc.select(".depart_third > div > a");
        for (Element e : es) {
            Mulu mulu1 = new Mulu();
            mulu1.setName(e.text());
            mulu1.setUrl(e.attr("href"));
            mulu1.setSource("9939");
            mulu1.setLevel(3);
            mulu1.setSpiderFlag(0);
            dao.Add(mulu1);
        }
    }
    public static void initQuestion(){
        List<JjQuestion> questions=new LinkedList<JjQuestion>();
        for(int i=4000000;i<5000000;i++){
            JjQuestion question=new JjQuestion();
            question.setOutId(i);
            question.setSpiderFlag(0);
            questions.add(question);
            if(questions.size()>100){
                jjDao.Add(questions);
                questions.clear();
            }
            jjDao.Add(questions);
        }
    }
}
