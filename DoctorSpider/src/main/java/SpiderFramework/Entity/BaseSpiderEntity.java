package SpiderFramework.Entity;

/**
 * Created by Administrator on 2016/6/2.
 */
public class BaseSpiderEntity {
    private long Id;
    private int SpiderFlag;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public int getSpiderFlag() {
        return SpiderFlag;
    }

    public void setSpiderFlag(int spiderFlag) {
        SpiderFlag = spiderFlag;
    }
}
