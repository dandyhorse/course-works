package hibernate.dao;

import hibernate.entity.Admin;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by User on 06.04.2016.
 */
public class AdminDao extends AbstractDao<Admin> {

    @Override
    public List<Admin> getAll() {
        inTransaction = new SessionInTransaction();
        return inTransaction.getAll(new CollectionHandler() {
            @Override
            public List<Admin> handle(Session session) {
                Criteria criteria = session.createCriteria(Admin.class);
                return criteria.list();
            }
        });
    }

    @Override
    public Admin getById(String i) {
        inTransaction = new SessionInTransaction();
        return inTransaction.getById(new SingleHandler() {
            @Override
            public Admin handle(Session session) {
                return session.load(Admin.class, i);
            }
        });
    }
}
