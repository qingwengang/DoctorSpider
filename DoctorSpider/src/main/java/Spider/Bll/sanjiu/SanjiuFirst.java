package Spider.Bll.sanjiu;

import Spider.Dao.MuluDao;
import Spider.Entity.Mulu;
import Util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.MessageFormat;

/**
 * Created by Administrator on 2016/6/16.
 */
public class SanjiuFirst {
    private static MuluDao dao = new MuluDao();

    public static void main(String[] args) {
        InitLevel1Mulu(47);
        InitLevel1Mulu(237);
        InitLevel1Mulu(44);
        InitLevel1Mulu(45);
        InitLevel1Mulu(3163);
        InitLevel1Mulu(323);
        InitLevel1Mulu(311);
        InitLevel1Mulu(319465592);
        InitLevel1Mulu(3162);
        InitLevel1Mulu(3165);
        InitLevel1Mulu(27);
        InitLevel1Mulu(3166);
    }

    private static void InitLevel1Mulu(int id) {
        String url = MessageFormat.format("http://ask.39.net/news/{0}-1.html", Integer.toString(id));
        Document doc = JsoupUtil.GetDocument(url);
        Element muluDiv = doc.select(".guige2").get(0);
        if (doc.getElementsByTag("ol") != null && doc.getElementsByTag("ol").size() > 0) {
            Elements lis = doc.getElementsByTag("ol").get(0).getElementsByTag("li");
            for (Element li : lis) {
                if (!li.text().equals("全部二级科室")) {
                    Element href = li.getElementsByTag("a").get(0);
                    Mulu mulu = new Mulu(href.text(), href.attr("href"), "39", 1, 0, "0", 0);
                    dao.Add(mulu);
                }
            }
        }else{
            System.out.println("++++++++++++++++++++++++"+id);
        }
    }
}
