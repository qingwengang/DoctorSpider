package Spider.Bll.School;

import Spider.Dao.SchoolContentDao;
import Spider.Dao.SchoolMuluDao;
import Spider.Entity.SchoolContent;
import Spider.Entity.SchoolMulu;
import Util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created by qingwengang on 2016/7/7.
 */
public class SchoolSpider {
    private SchoolMuluDao muluDao=new SchoolMuluDao();
    private SchoolContentDao contentDao=new SchoolContentDao();
    public void GetContent() {
        String sql = "select  * from schoolmulu where spiderflag=0 LIMIT 0,10";
        List<SchoolMulu> muluList = muluDao.Query(sql);
        while (muluList != null && muluList.size() > 0) {
            for (SchoolMulu mulu : muluList) {
                try {
                    DoBll(mulu);
                } catch (Exception e) {
                    mulu.setSpiderFlag(2);
                }
                muluDao.Update(mulu);
            }
            muluList = muluDao.Query(sql);
        }

    }
    private void DoBll(SchoolMulu mulu){
        String url="";
        if(mulu.getOutUrl().toLowerCase().startsWith("http:")){
            url=mulu.getOutUrl();
        }else if(mulu.getOutUrl().startsWith("/")){
            url="http://www.runoob.com"+mulu.getOutUrl();
        }else{
            String[] urls=mulu.getOutUrl().split("-");
            url= MessageFormat.format("http://www.runoob.com/{0}/{1}", urls[0], mulu.getOutUrl());
        }
        Document doc = JsoupUtil.GetDocument(url);
        Element divContent=doc.select("#content").get(0);
        Elements imgs=divContent.getElementsByTag("img");
        for(Element img : imgs){
            String src=img.attr("src");
            if(src.startsWith("/")){
                img.attr("src","http://www.runoob.com"+src);
            }
        }
        Elements hrefs=divContent.getElementsByTag("a");
        for(Element href : hrefs){
            String hrefUrl=href.attr("href");
            if(hrefUrl.startsWith("/")){
                href.attr("href","http://www.runoob.com"+hrefUrl);
            }
        }
        Elements h1=divContent.getElementsByTag("h1");
        Elements h2=divContent.getElementsByTag("h2");
        h1.addAll(h2);
        StringBuilder sbH=new StringBuilder();
        for(Element h : h1){
            sbH.append(h.text()).append("|");
        }
        String content=divContent.html();
        SchoolContent schoolContent=new SchoolContent();
        schoolContent.setMuluId(mulu.getId());
        schoolContent.setOutContent(content);
        schoolContent.setTitles(sbH.toString());
        contentDao.Add(schoolContent);
        mulu.setSpiderFlag(1);

    }
}
