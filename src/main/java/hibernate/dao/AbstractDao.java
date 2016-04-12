package hibernate.dao;

import java.util.List;

/**
 * Created by User on 06.04.2016.
 */
public abstract class AbstractDao<T> {
    protected SessionInTransaction inTransaction;

    public abstract List<T> getAll();

    public abstract T getById(String i);

    public void add(T t) {
        inTransaction = new SessionInTransaction();
        inTransaction.save(t);
    }

    public void update(T t) {
        inTransaction = new SessionInTransaction();
        inTransaction.update(t);
    }

    public void delete(T t) {
        inTransaction = new SessionInTransaction();
        inTransaction.delete(t);
    }

}
