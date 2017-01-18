package hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * Created by User on 06.04.2016.
 * Hides Session and Transaction in self
 * so we don't need use them anywhere else
 */
@Component
@Lazy
public class SessionInTransaction {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public <T> void save(T t) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(t);
        commitAndClose(transaction, session);
    }

    public <T> void update(T t) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(t);
        commitAndClose(transaction, session);
    }

    public <T> void delete(T t) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(t);
        commitAndClose(transaction, session);
    }

    public <T> List<T> getAll(CollectionHandler<T> collectionHandler) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Collection<T> list = collectionHandler.handle(session);
        commitAndClose(transaction, session);
        return (List<T>) list;
    }

    public <T> T getById(SingleHandler<T> singleHandler) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        T t = singleHandler.handle(session);
        commitAndClose(transaction, session);
        return t;
    }

    private void commitAndClose(Transaction transaction, Session session) {
        transaction.commit();
        session.close();
    }


}
