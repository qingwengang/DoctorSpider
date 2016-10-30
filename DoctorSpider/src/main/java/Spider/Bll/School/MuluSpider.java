package Spider.Bll.School;

import Spider.Dao.SchoolMuluDao;
import Spider.Entity.SchoolMulu;
import Util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by qingwengang on 2016/7/7.
 */
public class MuluSpider {
    private SchoolMuluDao muluDao=new SchoolMuluDao();
    public void GetMulu(String url){
        Document doc = JsoupUtil.GetDocument(url);
        Element left=doc.select(".left-column").get(0);
        String type1=left.select(".tab").get(0).text();
        String type2="";
        Element div=left.select(".design").get(0);
        for(Element child : div.children()){
            if(child.tagName().equals("a")){
                SchoolMulu mulu=new SchoolMulu();
                mulu.setName(child.text().trim());
                mulu.setOutUrl(child.attr("href"));
                mulu.setType1(type1);
                mulu.setType2(type2);
                mulu.setSpiderFlag(0);
                muluDao.Add(mulu);
            }else if(child.tagName().equals("h2")){
                type2=child.text().trim();
            }
        }
    }
}
