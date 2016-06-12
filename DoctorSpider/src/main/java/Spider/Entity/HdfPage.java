package Spider.Entity;

import SpiderFramework.Entity.BaseSpiderEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Administrator on 2016/5/12.
 */
@Entity
public class HdfPage extends BaseSpiderEntity{
    private String Riqi;
    private String Url;

    public String getRiqi() {
        return Riqi;
    }

    public void setRiqi(String riqi) {
        Riqi = riqi;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

}
