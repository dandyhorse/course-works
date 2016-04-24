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
        CollectionHandler<User> handler = session -> {
            Criteria criteria = session.createCriteria(User.class);
            return criteria.list();
        };
        return sessionInTransaction.getAll(handler);
    }

    @Override
    public User getByPK(String login) {
        SingleHandler<User> handler = session -> session.load(User.class, Integer.parseInt(login));
        return sessionInTransaction.getById(handler);
    }

}
