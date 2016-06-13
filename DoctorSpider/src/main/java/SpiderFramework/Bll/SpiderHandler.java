package SpiderFramework.Bll;

import Spider.Dao.BaseDao;
import SpiderFramework.Entity.BaseSpiderEntity;
import Util.LogUtil;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/6/2.
 */
public abstract class SpiderHandler<T extends BaseSpiderEntity,F extends BaseSpiderEntity> {
    private String spiderName;
    private int threadCount;
    private int threadNo;
    private String getUnspiderDataSql;
    private BaseDao<T> dao;
    private BaseDao<F> daoF;
    private List<F> addEntities;
    public SpiderHandler(String spiderName){
        this.spiderName=spiderName;
    }
    public SpiderHandler(String spiderName,int threadCount,int threadNo){
        this(spiderName);
        this.threadCount=threadCount;
        this.threadNo=threadNo;
    }
    public SpiderHandler(String spiderName,int threadCount,int threadNo,BaseDao<T> dao,BaseDao<F> daoF){
        this(spiderName,threadCount,threadNo);
        this.dao=dao;
        this.daoF=daoF;
    }
    public List<T> getUnspiderData(){
        return dao.Query(getUnspiderDataSql);
    }

    public void DoSpider(){
        LogUtil.Log120Ask(spiderName+":"+threadNo+"开始！");
        List<T> unspiderdata=getUnspiderData();
        while(unspiderdata!=null && unspiderdata.size()>0){
            for(T t : unspiderdata){
                int spiderFlag=1;
                try{
                    t.setSpiderFlag(99);
                    SpiderBll(t);
                    if(addEntities!=null && addEntities.size()>0){
                        daoF.Add(addEntities);
                    }
                }catch (Exception e){
                    spiderFlag=2;
                }
                t.setSpiderFlag(spiderFlag);
                dao.Update(t);
            }
            unspiderdata=getUnspiderData();
        }
        LogUtil.Log120Ask(spiderName+":"+threadNo+"结束！");
    }
    public abstract void SpiderBll(T t) throws IOException;

    public int getThreadCount() {
        return threadCount;
    }


    public int getThreadNo() {
        return threadNo;
    }

    public String getSpiderName() {
        return spiderName;
    }

    public void setSpiderName(String spiderName) {
        this.spiderName = spiderName;
    }

    public String getGetUnspiderDataSql() {
        return getUnspiderDataSql;
    }

    public void setGetUnspiderDataSql(String getUnspiderDataSql) {
        this.getUnspiderDataSql = getUnspiderDataSql;
    }

    public BaseDao<T> getDao() {
        return dao;
    }

    public void setDao(BaseDao<T> dao) {
        this.dao = dao;
    }

    public BaseDao<F> getDaoF() {
        return daoF;
    }

    public void setDaoF(BaseDao<F> daoF) {
        this.daoF = daoF;
    }

    public List<F> getAddEntities() {
        return addEntities;
    }

    public void setAddEntities(List<F> addEntities) {
        this.addEntities = addEntities;
    }
}
