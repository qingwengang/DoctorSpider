package Spider.Entity;

import SpiderFramework.Entity.BaseSpiderEntity;

import javax.persistence.Entity;

/**
 * Created by Administrator on 2016/6/10.
 */
@Entity
public class FHQuestion extends BaseSpiderEntity {
    private String OutId;
    private String MuluId;
    private String MuluName;
    private int WriteFileFlag;
    public String getOutId() {
        return OutId;
    }

    public void setOutId(String outId) {
        OutId = outId;
    }

    public String getMuluId() {
        return MuluId;
    }

    public void setMuluId(String muluId) {
        MuluId = muluId;
    }

    public String getMuluName() {
        return MuluName;
    }

    public void setMuluName(String muluName) {
        MuluName = muluName;
    }

    public int getWriteFileFlag() {
        return WriteFileFlag;
    }

    public void setWriteFileFlag(int writeFileFlag) {
        WriteFileFlag = writeFileFlag;
    }
}
