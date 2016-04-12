package hibernate.dao;

import org.hibernate.Session;

import java.util.Collection;
import java.util.List;

/**
 * Created by User on 06.04.2016.
 */
@FunctionalInterface
public interface CollectionHandler {
    <T> Collection<T> handle(Session session);
}
