package Spider.Bll.Thread;

import Spider.Dao.CSDNContentDao;
import Spider.Dao.CSDNMuluDao;
import Spider.Entity.CSDNContent;
import Spider.Entity.CSDNMulu;
import Util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/10/29.
 */
public class SpiderCSDNThread implements Runnable {
    private CSDNMuluDao muluDao=new CSDNMuluDao();
    private CSDNContentDao contentDao=new CSDNContentDao();
    public SpiderCSDNThread(int count,int no){
        this.count=count;
        this.no=no;
    }


    @Override
    public void run() {
        String sql= MessageFormat.format("select * from csdnmulu where id%{0}={1} and spiderflag=0 limit 0,1",count,no);
        List<CSDNMulu> muluList=muluDao.Query(sql);
        while(muluList!=null && muluList.size()>0){
            for(CSDNMulu mulu : muluList){
                mulu.setSpiderFlag(1);
                muluDao.Update(mulu);
                String url="http://bbs.csdn.net/"+mulu.getUrl();
                Document doc= JsoupUtil.GetDocument(url);
                Elements bodys=doc.select(".post_body");
                for(Element body : bodys){
                    String content=body.ownText().trim();
                    if(content.length()<=200 && content.length()>0){
                        CSDNContent csdnContent=new CSDNContent();
                        csdnContent.setContent(content);
                        csdnContent.setMuluId(mulu.getId());
                        csdnContent.setType(mulu.getType());
                        contentDao.Add(csdnContent);
                    }
                }
            }
            muluList=muluDao.Query(sql);
        }
    }
    private int count;
    private int no;
}
