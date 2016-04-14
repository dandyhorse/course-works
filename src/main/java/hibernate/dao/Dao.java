package hibernate.dao;

import java.util.List;

/**
 * Created by User on 14.04.2016.
 *
 * Common functionality ( CRUD ) for Data Access Object
 *
 */
public interface Dao<T> {

    List<T> getAll();

    T getByPK(String i);

    void add(T t);

    void update(T t);

    void delete(T t);

}
