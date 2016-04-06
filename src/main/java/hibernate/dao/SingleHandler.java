package hibernate.dao;

import org.hibernate.Session;

/**
 * Created by User on 06.04.2016.
 */
@FunctionalInterface
public interface SingleHandler {
    <T> T handle(Session session);
}
