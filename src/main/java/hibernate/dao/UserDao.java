package hibernate.dao;

import hibernate.HibernateUtil;
import hibernate.entity.User;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Vladimir on 04.04.2016.
 */
public class UserDao extends AbstractDao<User> {
    private final String sqlQuery = "select * from userr";

    @Override
    public List<User> getAll() {
        SessionInTransaction sessionInTransaction = new SessionInTransaction();
        return sessionInTransaction.getAll(new ListHandler() {
            @Override
            public List<User> handle(Session session) {
                return session.createSQLQuery(sqlQuery).addEntity(User.class).list();
            }
        });
    }

    @Override
    public User getById(String i) {
        SessionInTransaction sessionInTransaction = new SessionInTransaction();
        return sessionInTransaction.getById(new SingleHandler() {
            @Override
            public User handle(Session session) {
                return session.load(User.class, i);
            }
        });
    }

}
