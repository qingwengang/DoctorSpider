package Spider.Bll.Hdf;

import Spider.Dao.HdfMuluDao;
import Spider.Entity.HdfMulu;
import Util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Created by Administrator on 2016/6/11.
 */
public class GetHdfMulu {
    private static HdfMuluDao muluDao=new HdfMuluDao();
    public static void main(String[]  args){
        GetLevel1Mulu();
        GetLevel2Mulu();
    }
    public static void GetLevel1Mulu(){
            String url="http://www.haodf.com/jibing/list.htm";
            Document doc = JsoupUtil.GetDocument(url);
            Elements divs=doc.select(".kstl");
            for(Element div : divs){
                Element href=div.getElementsByTag("a").get(0);
                String muluurl=href.attr("href");
                String name=href.text();
                HdfMulu mulu=new HdfMulu();
                mulu.setLevel(1);
                mulu.setName(name);
                mulu.setUrl(muluurl);
                mulu.setSpiderFlag(0);
                muluDao.Add(mulu);
            }
    }
    public  static void GetLevel2Mulu(){
        List<HdfMulu> levelMulus=muluDao.Query("select * from hdfmulu where level=1");
        for(HdfMulu mulu : levelMulus){
            String url="http://www.haodf.com"+mulu.getUrl();
            Document doc = JsoupUtil.GetDocument(url);
            Element m_box_green=doc.select(".m_box_green").get(0);
            Element divCT=m_box_green.select(".ct").get(0);
            Elements divs=divCT.getElementsByTag("div");
            String tagName="";
            for(Element div : divs){
                if(div.attr("class").equals("m_title_green")){
                    try {
                        tagName = div.getElementsByTag("span").get(0).text();
                    }catch (Exception e){
                        tagName=div.text();
                    }
                }
                if(div.attr("class").equals("m_ctt_green")){
                    Elements hrefs=div.getElementsByTag("a");
                    for(Element href : hrefs){
                       HdfMulu hdfMulu=new HdfMulu();
                        hdfMulu.setUrl(href.attr("href"));
                        hdfMulu.setName(href.text());
                        hdfMulu.setLevel(2);
                        hdfMulu.setTagName(tagName);
                        muluDao.Add(hdfMulu);
                    }
                }
            }
        }
    }
}
