package Spider.Bll.Hdf;

import Spider.Dao.HdfPageDao;
import Spider.Entity.HdfPage;
import Util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Administrator on 2016/5/12.
 */
public class GetHaodaifu {
    private static HdfPageDao pageDao=new HdfPageDao();
    public static void main(String[] a){
        GetPageUrl();
    }
    public static void GetPageUrl(){
        for(int year=2008;year<=2016;year++){
            String url=String.format("http://www.haodf.com/sitemap-zx/%d/",year);
            Document doc = JsoupUtil.GetDocument(url);
            Elements divs=doc.select(".dis_article_2");
            for(Element div : divs){
                if(div.text().contains("按日期查找咨询")){
                    Elements hrefs=div.getElementsByTag("a");
                    for(Element href : hrefs){
                        HdfPage page=new HdfPage();
                        page.setUrl(href.attr("href"));
                        page.setRiqi(href.text());
                        pageDao.Add(page);
                    }
                }
            }
        }
    }
}
