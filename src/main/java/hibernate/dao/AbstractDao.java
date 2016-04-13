package hibernate.dao;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by User on 06.04.2016.
 */
public abstract class AbstractDao<T> {

    @Autowired
    protected SessionInTransaction sessionInTransaction;

    public void setSessionInTransaction(SessionInTransaction sessionInTransaction) {
        this.sessionInTransaction = sessionInTransaction;
    }

    public abstract List<T> getAll();

    public abstract T getById(String i);

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
