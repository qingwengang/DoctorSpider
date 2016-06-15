package Spider.Entity;

import SpiderFramework.Entity.BaseSpiderEntity;

/**
 * Created by Administrator on 2016/6/15.
 */
public class BaseQuestion extends BaseSpiderEntity {
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
