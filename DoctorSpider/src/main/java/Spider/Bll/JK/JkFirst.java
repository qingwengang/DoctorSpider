package Spider.Bll.JK;

import Spider.Dao.MuluDao;
import Spider.Entity.Mulu;
import Util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Administrator on 2016/6/15.
 */
public class JkFirst {
    private static MuluDao dao=new MuluDao();
    public static void main(String[] args){
        initMulu();
    }
    public static void initMulu(){
        String url="http://www.jiankang.com/ask/keshi/";
        Document doc = JsoupUtil.GetDocument(url);
        Elements es=doc.select(".aaq_unit_dl");
        for(Element e : es){
            Element level1=e.getElementsByTag("dt").get(0).getElementsByTag("a").get(0);
            Mulu mulu=new Mulu();
            mulu.setName(level1.text());
            mulu.setUrl(level1.attr("href"));
            mulu.setLevel(1);
            mulu.setSource("JK");
            dao.Add(mulu);
            Elements dds=e.getElementsByTag("dd");
            for(Element dd : dds){
                if(dd.attr("class").contains("first")){
                    Elements hrefs=dd.getElementsByTag("a");
                    for(Element href : hrefs){
                        Mulu mulu2=new Mulu();
                        mulu2.setName(href.text());
                        mulu2.setUrl(href.attr("href"));
                        mulu2.setLevel(2);
                        mulu2.setSource("JK");
                        dao.Add(mulu2);
                    }
                }else{
                    Elements hrefs=dd.getElementsByTag("a");
                    for(Element href : hrefs){
                        Mulu mulu2=new Mulu();
                        mulu2.setName(href.text());
                        mulu2.setUrl(href.attr("href"));
                        mulu2.setLevel(3);
                        mulu2.setSource("JK");
                        dao.Add(mulu2);
                    }
                }
            }
        }
    }
}


