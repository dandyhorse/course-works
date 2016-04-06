package hibernate.dao;

import org.hibernate.Session;

import java.util.List;

/**
 * Created by User on 06.04.2016.
 */
@FunctionalInterface
public interface ListHandler {
    <T> List<T> handle(Session session);
}
