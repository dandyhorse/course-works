package hibernate.dao;

import hibernate.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Vladimir on 04.04.2016.
 */
@Component
@Lazy
public class UserDao extends AbstractDao<User> {

    @Override
    public List<User> getAll() {
        return sessionInTransaction.getAll(new CollectionHandler() {
            @Override
            public List<User> handle(Session session) {
                Criteria criteria = session.createCriteria(User.class);
                return criteria.list();
            }
        });
    }

    @Override
    public User getByPK(String login) {
        return sessionInTransaction.getById(new SingleHandler() {
            @Override
            public User handle(Session session) {
                return session.load(User.class, login);
            }
        });
    }

}
