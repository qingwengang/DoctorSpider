package SpiderFramework.Bll;

import SpiderFramework.Entity.BaseSpiderEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2.
 */
public abstract class SpiderHandler<T extends BaseSpiderEntity> {
    private String spiderName;
    public abstract List<T> getUnspiderData();

    public void DoSpider(){
        List<T> unspiderdata=getUnspiderData();
        while(unspiderdata!=null && unspiderdata.size()>0){
            for(T t : unspiderdata){
                int spiderFlag=1;
                try{
                    SpiderBll(t);
                }catch (Exception e){
                    spiderFlag=2;
                }
                t.setSpiderFlag(spiderFlag);
            }
            unspiderdata=getUnspiderData();
        }
    }
    public abstract void SpiderBll(T t);
    public abstract void Update(T t);
}
