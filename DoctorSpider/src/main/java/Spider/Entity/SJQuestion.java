package Spider.Entity;

import javax.persistence.Entity;

/**
 * Created by Administrator on 2016/6/17.
 */
@Entity
public class SJQuestion extends BaseQuestion {
    private int ifCreated;
    private int ifWriteFile;

    public int getIfWriteFile() {
        return ifWriteFile;
    }

    public void setIfWriteFile(int ifWriteFile) {
        this.ifWriteFile = ifWriteFile;
    }

    public int getIfCreated() {
        return ifCreated;
    }

    public void setIfCreated(int ifCreated) {
        this.ifCreated = ifCreated;
    }
}
