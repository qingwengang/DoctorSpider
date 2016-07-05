package Spider.Entity;

import SpiderFramework.Entity.BaseSpiderEntity;

import javax.persistence.Entity;

/**
 * Created by Administrator on 2016/6/13.
 */
@Entity
public class JjQuestion extends BaseSpiderEntity{
    private String TypeName;
    private long OutId;
    private Integer IfCreated;

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public long getOutId() {
        return OutId;
    }

    public void setOutId(long outId) {
        OutId = outId;
    }

    public Integer getIfCreated() {
        return IfCreated;
    }

    public void setIfCreated(Integer ifCreated) {
        IfCreated = ifCreated;
    }
}
