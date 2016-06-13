package Spider.Dao;

import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/10.
 */
public class BaseDao<T> {
    private Class<T> entityClass;
    public BaseDao() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = (Class) params[0];
    }
    public <T> void Add(T entity) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }
    public void Add(List<T> entities){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx=session.beginTransaction();
        int i=0;
        for(T t : entities){
            session.save(t);
            i++;
            if(i%50==0){
                session.flush();
                session.clear();
                tx.commit();
                tx = session.beginTransaction();
            }
        }
        tx.commit();
        session.close();
    }
    public <T> List<T> Query(String sql) {
        List l;
        List<T> result=new LinkedList<T>();
        SessionFactory sf=HibernateUtil.getSessionFactory();
        Session session=sf.openSession();
        session.beginTransaction();
        l=session.createSQLQuery(sql).addEntity(entityClass).list();
        for (Object o : l) {
            result.add((T)o);
        }
        session.getTransaction().commit();
        session.close();
        return result;
    }
    public <T> void Update(T entity){
        SessionFactory sf=HibernateUtil.getSessionFactory();
        Session session=sf.openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }
    public List<String> GetOneColumn(String columnName,String tableName){
        SessionFactory sf=HibernateUtil.getSessionFactory();
        Session session=sf.openSession();
        List<String> ls=new LinkedList<String>();
        List list=session.createSQLQuery(String.format("select DISTINCT %s from %s",columnName,tableName)).list();
        for(Iterator iterator =list.iterator();iterator.hasNext();){
            ls.add(iterator.next().toString());
        }
        session.close();
        return ls;
    }
}
