package Spider.Bll.Thread.School;

import Spider.DO.School.SchoolElement;
import Util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by Administrator on 2017/2/15.
 */
public abstract class BaseSchoolSpider {
    private SchoolElement sElement;
    public void Spider(String url,String codeType){
        url="http://www.javatpoint.com/"+url;
        Document doc = JsoupUtil.GetDocument(url);
        Element articleElement = doc.getElementById("#city").getElementsByTag("table").get(0).getElementsByTag("tbody").get(0).getElementsByTag("tr").get(0).getElementsByTag("td").get(0);
        sElement = new SchoolElement();
        for (Element child : articleElement.children()) {

        }
    }
    private void AddTextElement(String tagType,String content){
        sElement.addChild(new SchoolElement(tagType, content));
    }
}
