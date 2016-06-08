package Spider.Bll;

import Util.JsoupUtil;
import org.jsoup.nodes.Document;

/**
 * Created by Administrator on 2016/6/8.
 */
public class SpiderFHQuestion {
    public static void main(String[] args){
        Spider(102785561);
    }
    private static void Spider(long id){
        String url="http://iask.fh21.com.cn/question/"+id+".html";
        Document doc = JsoupUtil.GetDocument(url);
        System.out.print(doc.select(".iask_navigator").get(0).text());
    }
}
