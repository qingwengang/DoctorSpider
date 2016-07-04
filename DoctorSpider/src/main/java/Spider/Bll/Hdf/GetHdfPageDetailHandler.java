package Spider.Bll.Hdf;

import Spider.Dao.HdfPageDao;
import Spider.Dao.HdfPageDetailDao;
import Spider.Entity.HdfPage;
import Spider.Entity.HdfPageDetail;
import SpiderFramework.Bll.SpiderHandler;
import SpiderFramework.Entity.BaseSpiderEntity;
import Util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

/**
 * 获取日期分页中每页的url
 */
public class GetHdfPageDetailHandler extends SpiderHandler<HdfPage,HdfPage> {
    private HdfPageDao dao=new HdfPageDao();
    private HdfPageDetailDao detailDao=new HdfPageDetailDao();
    public GetHdfPageDetailHandler() {
        super("获取hdf日期分页",1,0,new HdfPageDao(),new HdfPageDao());
    }

    public GetHdfPageDetailHandler( int threadCount, int threadNo) {
        super("获取hdf日期分页", threadCount, threadNo,new HdfPageDao(),new HdfPageDao());
    }

    @Override
    public List<HdfPage> getUnspiderData() {
        String sql= MessageFormat.format("select * from hdfpage where SpiderFlag=0 and id%{0}={1} LIMIT 0,10",getThreadCount(),getThreadNo());
        List<HdfPage> pages=dao.Query(sql);
        return pages;
    }

    @Override
    public void SpiderBll(HdfPage hdfPage) throws IOException {
        Document doc = JsoupUtil.GetDocument(hdfPage.getUrl());
        Element pageDiv=doc.select(".p_bar").get(0);
        int pageCount=0;
        for(Element href : pageDiv.getElementsByTag("a")){
            if(href.text().equals("尾页")){
                String url=href.attr("href");
                String path=url.substring(0, url.indexOf("_"));
                String no=url.substring(url.indexOf("_")+1).replace("/", "");
                int iNo=Integer.parseInt(no);
                for(int i=1;i<=iNo;i++){
                    HdfPageDetail pageDetail=new HdfPageDetail();
                    pageDetail.setUrl(path + "_" + i + "/");
                    pageDetail.setSpiderFlag(0);
                    detailDao.Add(pageDetail);
                }
            }
        }
    }
}
