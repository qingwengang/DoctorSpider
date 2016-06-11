package Spider.Bll.Hdf;

import Spider.Dao.HdfMuluDao;
import Spider.Entity.HdfMulu;
import SpiderFramework.Bll.SpiderHandler;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/6/11.
 */
public class GetHdfQuestionListHandler extends SpiderHandler<HdfMulu> {
    private HdfMuluDao muluDao=new HdfMuluDao();
    public GetHdfQuestionListHandler(){
        super("获取hdf的问题列表",1,0);
    }
    public GetHdfQuestionListHandler(int handlerCount,int handlerNo){
        super("获取hdf的问题列表",handlerCount,handlerNo);
    }
    @Override
    public List<HdfMulu> getUnspiderData() {
        String sql= MessageFormat.format("select * from hdfmulu where SpiderFlag=0 and level=2  and id%{0}={1} LIMIT 0,10", getThreadCount(), getThreadNo());
        List<HdfMulu> questions=muluDao.Query(sql);
        return questions;
    }

    @Override
    public void SpiderBll(HdfMulu hdfMulu) throws IOException {

    }

    @Override
    public void Update(HdfMulu hdfMulu) {
        muluDao.Update(hdfMulu);
    }
}
