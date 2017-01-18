package hibernate.dao;

import org.hibernate.Session;

/**
 * Created by User on 06.04.2016.
 * This functional interface is using to help create custom quarry to get
 * an object from database
 * with no care about closing Session and committing Transaction
 */
@FunctionalInterface
public interface SingleHandler<T> {
    T handle(Session session);
}
