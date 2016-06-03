package SpiderFramework.Entity;

import org.hibernate.metamodel.binding.InheritanceType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;

/**
 * Created by Administrator on 2016/6/2.
 */
@MappedSuperclass
@Inheritance(strategy = javax.persistence.InheritanceType.TABLE_PER_CLASS)
public class BaseSpiderEntity {
    private long Id;
    private int SpiderFlag;
    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
