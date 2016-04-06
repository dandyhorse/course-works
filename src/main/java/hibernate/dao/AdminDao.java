package hibernate.dao;

import hibernate.entity.Admin;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by User on 06.04.2016.
 */
public class AdminDao extends AbstractDao<Admin> {
    private final String sqlQuery = "select * from admin";

    @Override
    public List<Admin> getAll() {
        SessionInTransaction sessionInTransaction = new SessionInTransaction();
        return sessionInTransaction.getAll(new ListHandler() {
            @Override
            public List<Admin> handle(Session session) {
                return session.createSQLQuery(sqlQuery).addEntity(Admin.class).list();
            }
        });
    }

    @Override
    public Admin getById(String i) {
        SessionInTransaction sessionInTransaction = new SessionInTransaction();
        return sessionInTransaction.getById(new SingleHandler() {
            @Override
            public Admin handle(Session session) {
                return session.load(Admin.class, i);
            }
        });
    }
}
