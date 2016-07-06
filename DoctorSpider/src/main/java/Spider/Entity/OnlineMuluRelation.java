package Spider.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by qingwengang on 2016/7/4.
 */
@Entity
public class OnlineMuluRelation {
    private long Id;
    private long OnlineMuluId;
    private String Ask120Relation;
    private String FHRelation;
    private String JJRelation;
    private String JKRelation;
    private String SJRelation;
    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getOnlineMuluId() {
        return OnlineMuluId;
    }

    public void setOnlineMuluId(long onlineMuluId) {
        OnlineMuluId = onlineMuluId;
    }

    public String getAsk120Relation() {
        return Ask120Relation;
    }

    public void setAsk120Relation(String ask120Relation) {
        Ask120Relation = ask120Relation;
    }

    public String getFHRelation() {
        return FHRelation;
    }

    public void setFHRelation(String FHRelation) {
        this.FHRelation = FHRelation;
    }

    public String getJJRelation() {
        return JJRelation;
    }

    public void setJJRelation(String JJRelation) {
        this.JJRelation = JJRelation;
    }

    public String getJKRelation() {
        return JKRelation;
    }

    public void setJKRelation(String JKRelation) {
        this.JKRelation = JKRelation;
    }

    public String getSJRelation() {
        return SJRelation;
    }

    public void setSJRelation(String SJRelation) {
        this.SJRelation = SJRelation;
    }
}
