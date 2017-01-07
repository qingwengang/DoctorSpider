package Spider.Bll.Thread;

import Spider.Dao.CnblogsDao;
import Spider.Entity.Cnblogs;
import Util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

/**
 * Created by Administrator on 2017/1/6.
 */
public class CnblogsContentThread implements Runnable {
    private CnblogsDao cnblogsDao=new CnblogsDao();
    @Override
    public void run() {
        String sql="select * from cnblogs where SpiderFlag=0";
        List<Cnblogs> muluList=cnblogsDao.Query(sql);
        while (muluList!=null && muluList.size()>0){
            for(Cnblogs cnblog : muluList){
                cnblog.setSpiderFlag(1);
                Document doc= JsoupUtil.GetDocument(cnblog.getUrl());
                Element e=doc.getElementById("cnblogs_post_body");
                cnblog.setContent(e.html());
                cnblogsDao.Update(cnblog);
            }
        }
    }
}
