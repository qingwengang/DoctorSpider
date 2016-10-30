package Spider.Dao;

import Spider.Entity.OnlineMuluRelation;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created by qingwengang on 2016/7/4.
 */
public class OnlineMuluRelationDao extends BaseDao<OnlineMuluRelation> {
    public OnlineMuluRelation GetByOnlineMuluId(long muluId){
        String sql= MessageFormat.format("SELECT * from onlinemulurelation where OnlineMuluId={0}",muluId);
        List<OnlineMuluRelation> relationList=this.Query(sql);
        OnlineMuluRelation relation=null;
        if(relationList!=null && relationList.size()>0){
            relation=relationList.get(0);
        }
        return relation;
    }
}
