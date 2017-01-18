package hibernate.dao;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by User on 06.04.2016.
 *
 * Implements ease methods and them delegate into
 * @link SessionInTransaction
 *
 */
public abstract class AbstractDao<T> implements Dao<T> {

    @Autowired
    protected SessionInTransaction sessionInTransaction;

    public void setSessionInTransaction(SessionInTransaction sessionInTransaction) {
        this.sessionInTransaction = sessionInTransaction;
    }

    public abstract List<T> getAll();

    public abstract T getByPK(String id);

    public void add(T t) {
        sessionInTransaction.save(t);
    }

    public void update(T t) {
        sessionInTransaction.update(t);
    }

    public void delete(T t) {
        sessionInTransaction.delete(t);
    }

}
