package Spider.Dao;

import Spider.Entity.Mulu;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/6/2.
 */
public class MuluDaoTest {
    private MuluDao dao=new MuluDao();
    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.Test
    public void testAdd() throws Exception {
        Mulu mulu=new Mulu();
        mulu.setName("卿文刚");
        dao.Add(mulu);
    }

    @org.junit.Test
    public void testQuery1() throws Exception {

    }
}