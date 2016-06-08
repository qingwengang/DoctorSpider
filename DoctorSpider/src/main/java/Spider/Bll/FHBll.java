package Spider.Bll;

import Spider.Dao.MuluDao;
import Spider.Entity.Mulu;
import Util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Created by Administrator on 2016/6/7.
 */
public class FHBll {
    public static MuluDao muluDao=new MuluDao();
    public static void getLevel1Mulu(){
        System.out.print("--------------------feihua getmulu 开始----------------------");
        String url = "http://iask.fh21.com.cn/";
        Document doc = JsoupUtil.GetDocument(url);
        Elements lis=doc.select(".iask02");
        Elements dts=lis.get(0).getElementsByTag("dt");
        for(Element dt : dts){
            Elements hrefs=dt.getElementsByTag("a");
            for(Element href : hrefs){
                Mulu mulu=new Mulu();
                mulu.setName(href.text());
                mulu.setUrl(href.attr("href"));
                mulu.setSource("fh");
                mulu.setSpiderFlag(0);
                mulu.setParentId(0);
                mulu.setLevel(1);
                muluDao.Add(mulu);
            }
        }
    }
    public static void getLevel2Mulu(){
        String sql="select * from mulu where Source='fh' and Level=1";
        List<Mulu> level1=muluDao.Query(sql);
        for(Mulu mulu : level1){
            System.out.print(mulu.getId());
            Document doc = JsoupUtil.GetDocument(mulu.getUrl());
            Elements div=doc.select(".iask09_tag");
            if(div==null || div.size()==0){
                div=doc.select(".iask09_tag02");
            }
            Elements hrefs=div.get(0).getElementsByTag("a");
            for(Element href : hrefs){
                Mulu mulu2=new Mulu();
                mulu2.setName(href.text());
                mulu2.setUrl(href.attr("href"));
                mulu2.setSource("fh");
                mulu2.setSpiderFlag(0);
                mulu2.setParentId(0);
                mulu2.setLevel(2);
                muluDao.Add(mulu2);
            }
        }
    }
    public static void getLevel3Mulu(){
        String sql="select * from mulu where Source='fh' and Level=2";
        List<Mulu> level1=muluDao.Query(sql);
        for(Mulu mulu : level1){
            Document doc = JsoupUtil.GetDocument("http://iask.fh21.com.cn"+mulu.getUrl());
            Elements div = doc.select(".iask09_con");
            Elements hrefs=div.get(0).getElementsByTag("a");
            for(Element href : hrefs){
                Mulu mulu2=new Mulu();
                mulu2.setName(href.text());
                mulu2.setUrl(href.attr("href"));
                mulu2.setSource("fh");
                mulu2.setSpiderFlag(0);
                mulu2.setParentId(0);
                mulu2.setLevel(3);
                muluDao.Add(mulu2);
            }
        }
    }
}
