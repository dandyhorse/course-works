package hibernate.dao;

import org.hibernate.Session;

import java.util.Collection;
import java.util.List;

/**
 * Created by User on 06.04.2016.
 * This functional interface is using to help create custom quarry to get
 * an collection of objects from database
 * with no care about closing Session and committing Transaction
 */
@FunctionalInterface
public interface CollectionHandler {
    <T> Collection<T> handle(Session session);
}
