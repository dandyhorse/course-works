package hibernate.dao;

import hibernate.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Vladimir on 04.04.2016.
 */
public class UserDao extends AbstractDao<User> {

    @Override
    public List<User> getAll() {
        inTransaction = new SessionInTransaction();
        return inTransaction.getAll(new CollectionHandler() {
            @Override
            public List<User> handle(Session session) {
                Criteria criteria = session.createCriteria(User.class);
                return criteria.list();
            }
        });
    }

    @Override
    public User getById(String i) {
        inTransaction = new SessionInTransaction();
        return inTransaction.getById(new SingleHandler() {
            @Override
            public User handle(Session session) {
                return session.load(User.class, i);
            }
        });
    }

}
