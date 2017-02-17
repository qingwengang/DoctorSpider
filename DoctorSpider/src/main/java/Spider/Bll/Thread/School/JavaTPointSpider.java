package Spider.Bll.Thread.School;

import Spider.DO.School.SchoolElement;
import Spider.Dao.SpiderSchoolDao;
import Spider.Entity.SpiderSchool;
import Util.JsoupUtil;
import com.sun.javafx.binding.StringFormatter;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/2/15.
 */
public class JavaTPointSpider {
    private static Logger logger = Logger.getLogger(JavaTPointSpider.class);
    private SchoolElement sElement;
    public void Spider(String url,String codeType){
        boolean flag=true;
        while(flag){
            String url1="http://www.javatpoint.com/"+url;
            Document doc = JsoupUtil.GetDocument(url1);
            Element articleElement = doc.getElementById("city").getElementsByTag("table").get(0).getElementsByTag("tbody").get(0).getElementsByTag("tr").get(0).getElementsByTag("td").get(0);
            sElement = new SchoolElement();
            spiderElement(articleElement,url1);
            ObjectMapper mapper = new ObjectMapper();
            String json = "";
            try {
                json = mapper.writeValueAsString(sElement);
            } catch (IOException e) {
            }
            SpiderSchool school = new SpiderSchool();
            school.setContent(json);
            school.setResource("javatpoint");
            school.setMuluType("java");
            school.setPageName(url);
            new SpiderSchoolDao().Add(school);
            Elements es = doc.select(".next");
            if(es!=null && es.size()>0){
                for(Element e : es){
                    if(e.ownText().contains("next")){
                        url=e.attr("href");
                        String sql="select * from spiderschool where resource='javatpoint' and pagename='"+url+"'";
                        List<SpiderSchool> sslist=new SpiderSchoolDao().Query(sql);
                        if(sslist!=null && sslist.size()>0){
                            flag=false;
                        }
                    }
                }
            }else {
                flag=false;
            }
        }
    }

    private void spiderElement(Element articleElement,String url1){
        for (Element child : articleElement.children()) {
            boolean ifParsed = false;
            try {
                if (child.tagName().equals("p") || child.tagName().equals("h1") || child.tagName().equals("h2") || child.tagName().equals("h3") || child.tagName().equals("h4")) {
                    SchoolElement ep = new SchoolElement(child.tagName(), child.text());
                    sElement.addChild(ep);
                    ifParsed = true;
                }
                if (child.tagName().equals("ul")) {
                    SchoolElement ul = new SchoolElement();
                    ul.setType("ul");
                    for (Element li : child.getElementsByTag("li")) {
                        ul.addChild(new SchoolElement("li", li.text()));
                    }
                    sElement.addChild(ul);
                }
                if (child.tagName().equals("ol")) {
                    SchoolElement ul = new SchoolElement();
                    ul.setType("ol");
                    for (Element li : child.getElementsByTag("li")) {
                        ul.addChild(new SchoolElement("li", li.text()));
                    }
                    sElement.addChild(ul);
                }
            }catch (Exception e){
                logger.error("jaratpoint1:"+url1,e);
            }
            if(child.tagName().equals("img")){
                try{
                    sElement.addChild(new SchoolElement("source_result", child.text()));
                }catch (Exception e){
                    logger.error("jaratpoint2:"+url1,e);
                }
            }
            if(child.tagName().equals("div") && child.attr("class").contains("codeblock")){
                try{
                    if(child.getElementsByTag("pre").size()>0){
                        sElement.addChild(new SchoolElement("source_result",child.getElementsByTag("pre").get(0).ownText()));
                    }
                    if(child.getElementsByTag("textarea").size()>0){
                        Element txt=child.getElementsByTag("textarea").get(0);
                        sElement.addChild(new SchoolElement("source_"+txt.attr("class"),txt.ownText()));
                    }
                }catch (Exception e){
                    logger.error("jaratpoint3:"+url1,e);
                }
            }
            if(child.tagName().equals("div") && child.attr("class").contains("codeblock3")) {
                try {
                    if (child.getElementsByTag("pre") != null && child.getElementsByTag("pre").size() > 0) {
                        sElement.addChild(new SchoolElement("source_result", child.getElementsByTag("pre").get(0).ownText()));
                    } else {
                        sElement.addChild(new SchoolElement("source_result", child.ownText()));
                    }
                } catch (Exception e) {
                    logger.error("jaratpoint4:" + url1, e);
                }
            }
            if(child.tagName().equals("div") && child.attr("class")==""){
                spiderElement(child,url1);
            }
            if(child.tagName().equals("table")){
                try{
                    SchoolElement tableElement = new SchoolElement();
                    tableElement.setType("table");
                    for (Element tr : child.getElementsByTag("tr")) {
                        SchoolElement trElement = new SchoolElement();
                        trElement.setType("tr");
                        for (Element td : tr.children()) {
                            trElement.addChild(new SchoolElement("td", td.text()));
                        }
                        tableElement.addChild(trElement);
                    }
                    sElement.addChild(tableElement);
                }catch (Exception e){
                    logger.error("jaratpoint5:"+url1,e);
                }
            }
        }
    }
}
