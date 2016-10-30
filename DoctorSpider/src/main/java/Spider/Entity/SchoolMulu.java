package Spider.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by qingwengang on 2016/7/7.
 */
@Entity
public class SchoolMulu {
    private long Id;
    private String Name;
    private double Sort;
    private String OutUrl;
    private String Type1;
    private String Type2;
    private int SpiderFlag;
    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getSort() {
        return Sort;
    }

    public void setSort(double sort) {
        Sort = sort;
    }

    public String getOutUrl() {
        return OutUrl;
    }

    public void setOutUrl(String outUrl) {
        OutUrl = outUrl;
    }

    public String getType1() {
        return Type1;
    }

    public void setType1(String type1) {
        Type1 = type1;
    }

    public String getType2() {
        return Type2;
    }

    public void setType2(String type2) {
        Type2 = type2;
    }

    public int getSpiderFlag() {
        return SpiderFlag;
    }

    public void setSpiderFlag(int spiderFlag) {
        SpiderFlag = spiderFlag;
    }
}
