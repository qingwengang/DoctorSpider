package Spider.Entity;

import SpiderFramework.Entity.BaseSpiderEntity;

import javax.persistence.Entity;

/**
 * Created by Administrator on 2016/6/12.
 */
@Entity
public class HdfPageDetail extends BaseSpiderEntity {
    private String Url;

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
