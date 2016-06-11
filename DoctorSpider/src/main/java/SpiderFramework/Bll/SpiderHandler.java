package SpiderFramework.Bll;

import SpiderFramework.Entity.BaseSpiderEntity;
import Util.LogUtil;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/6/2.
 */
public abstract class SpiderHandler<T extends BaseSpiderEntity> {
    private String spiderName;
    private int threadCount;
    private int threadNo;
    public SpiderHandler(String spiderName){
        this.spiderName=spiderName;
    }
    public SpiderHandler(String spiderName,int threadCount,int threadNo){
        this.spiderName=spiderName;
        this.threadCount=threadCount;
        this.threadNo=threadNo;
    }
    public abstract List<T> getUnspiderData();

    public void DoSpider(){
        LogUtil.Log120Ask(spiderName+":"+threadNo+"开始！");
        List<T> unspiderdata=getUnspiderData();
        while(unspiderdata!=null && unspiderdata.size()>0){
            for(T t : unspiderdata){
                int spiderFlag=1;
                try{
                    t.setSpiderFlag(99);
                    SpiderBll(t);
                }catch (Exception e){
                    spiderFlag=2;
                }
                t.setSpiderFlag(spiderFlag);
                Update(t);
            }
            unspiderdata=getUnspiderData();
        }
        LogUtil.Log120Ask(spiderName+":"+threadNo+"结束！");
    }
    public abstract void SpiderBll(T t) throws IOException;
    public abstract void Update(T t);

    public int getThreadCount() {
        return threadCount;
    }


    public int getThreadNo() {
        return threadNo;
    }

}
