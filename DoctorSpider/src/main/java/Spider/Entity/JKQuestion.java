package Spider.Entity;

import SpiderFramework.Entity.BaseSpiderEntity;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by Administrator on 2016/6/15.
 */
@Entity
public class JKQuestion extends BaseSpiderEntity {
    private String TypeName;
    private long OutId;
    private Date PublishTime;
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

    public Date getPublishTime() {
        return PublishTime;
    }

    public void setPublishTime(Date publishTime) {
        PublishTime = publishTime;
    }
}
