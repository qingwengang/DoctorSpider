package Spider.Entity;

import SpiderFramework.Entity.BaseSpiderEntity;

import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by Administrator on 2016/6/15.
 */
@MappedSuperclass
@Inheritance(strategy = javax.persistence.InheritanceType.TABLE_PER_CLASS)
public class BaseQuestion extends BaseSpiderEntity {
    private String TypeName;
    private String OutId;
    private Date PublishTime;
    private Date SpiderTime;
    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getOutId() {
        return OutId;
    }

    public void setOutId(String outId) {
        OutId = outId;
    }

    public Date getPublishTime() {
        return PublishTime;
    }

    public void setPublishTime(Date publishTime) {
        PublishTime = publishTime;
    }

    public Date getSpiderTime() {
        return SpiderTime;
    }

    public void setSpiderTime(Date spiderTime) {
        SpiderTime = spiderTime;
    }
}
