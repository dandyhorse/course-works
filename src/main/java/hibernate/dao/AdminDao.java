package hibernate.dao;

import hibernate.entity.Admin;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by User on 06.04.2016.
 */
@Component
@Lazy
public class AdminDao extends AbstractDao<Admin> {

    @Override
    public List<Admin> getAll() {
        return sessionInTransaction.getAll(new CollectionHandler() {
            @Override
            public List<Admin> handle(Session session) {
                Criteria criteria = session.createCriteria(Admin.class);
                return criteria.list();
            }
        });
    }

    @Override
    public Admin getById(String i) {
        return sessionInTransaction.getById(new SingleHandler() {
            @Override
            public Admin handle(Session session) {
                return session.load(Admin.class, i);
            }
        });
    }
}
