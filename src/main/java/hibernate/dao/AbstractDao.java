package hibernate.dao;

import java.util.List;

/**
 * Created by User on 06.04.2016.
 */
public abstract class AbstractDao<T> {

    public abstract List<T> getAll();

    public abstract T getById(String i);

    public void add(T t) {
        SessionInTransaction inTransaction = new SessionInTransaction();
        inTransaction.save(t);
    }

    public void update(T t) {
        SessionInTransaction inTransaction = new SessionInTransaction();
        inTransaction.update(t);
    }

    public void delete(T t) {
        SessionInTransaction inTransaction = new SessionInTransaction();
        inTransaction.delete(t);
    }

}
