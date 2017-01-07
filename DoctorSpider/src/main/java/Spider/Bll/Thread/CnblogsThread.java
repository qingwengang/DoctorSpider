package Spider.Bll.Thread;

import Spider.Dao.CnblogsDao;
import Spider.Entity.Cnblogs;
import Util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * Created by Administrator on 2017/1/6.
 */
public class CnblogsThread implements Runnable {
    private CnblogsDao cnblogsDao=new CnblogsDao();
    @Override
    public void run() {
        for(int i=200;i>=1;i--){
            String url="http://www.cnblogs.com/cate/csharp/#p"+i;
            Document doc= JsoupUtil.GetDocument(url);
            Elements es=doc.select(".titlelnk");
            for(Element link : es){
                Cnblogs cnblogs=new Cnblogs();
                cnblogs.setName(link.text());
                cnblogs.setUrl(link.attr("href"));
                cnblogs.setType("csharp");
                cnblogsDao.Add(cnblogs);
            }
        }
    }
}
