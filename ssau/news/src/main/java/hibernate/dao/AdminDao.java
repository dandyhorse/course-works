package hibernate.dao;

import hibernate.entity.Admin;
import hibernate.entity.News;
import hibernate.entity.Repository;
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
        CollectionHandler<Admin> handler = session -> {
            Criteria criteria = session.createCriteria(Admin.class);
            return criteria.list();
        };
        return sessionInTransaction.getAll(handler);
    }

    @Override
    public Admin getByPK(String login) {
        SingleHandler<Admin> handler = session -> session.load(Admin.class, Integer.parseInt(login));
        return sessionInTransaction.getById(handler);
    }
}
