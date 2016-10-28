package Spider.Bll.School;

import Util.JsoupUtil;
import com.mongodb.DBPortPool;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;

/**
 * Created by Administrator on 2016/10/28.
 */
public class CsdnSpider {
    public static void getmulu(String typeName){
        String url="http://bbs.csdn.net/forums/JavaScript?page=500";
        Document doc = JsoupUtil.GetDocument(url);
        Elements titles=doc.select(".table_list").get(0).select(".title");
        for(Element title : titles){
            System.out.println(title.text());
        }
    }
    public static void getContent(String url){
        Document doc=JsoupUtil.GetDocument("http://bbs.csdn.net/topics/190144794");

    }
}
