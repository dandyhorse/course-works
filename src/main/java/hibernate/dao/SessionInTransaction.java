package hibernate.dao;

import hibernate.HibernateUtil;
import hibernate.entity.Repository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by User on 06.04.2016.
 */
public class SessionInTransaction {
    private Session session;
    private Transaction transaction;


    public SessionInTransaction() {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    public <T> void save(T t) {
        session.save(t);
        commitAndClose();
    }

    public <T> void update(T t) {
        session.update(t);
        commitAndClose();
    }

    public <T> void delete(T t) {
        session.delete(t);
        commitAndClose();
    }

    private void commitAndClose() {
        transaction.commit();
        session.close();
    }

    public <T> List<T> getAll(ListHandler listHandler) {
        List<T> list = listHandler.handle(session);
        commitAndClose();
        return list;
    }

    public <T> T getById(SingleHandler singleHandler) {
        T t = singleHandler.handle(session);
        commitAndClose();
        return t;
    }
}
