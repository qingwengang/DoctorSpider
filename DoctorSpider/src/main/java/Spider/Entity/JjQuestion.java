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
}
