package hibernate.dao;

import hibernate.entity.Repository;
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
public class RepositoryDao extends AbstractDao<Repository>{

    @Override
    public List<Repository> getAll() {
        CollectionHandler<Repository> handler = session -> {
            Criteria criteria = session.createCriteria(Repository.class);
            return criteria.list();
        };
        return sessionInTransaction.getAll(handler);
    }

    @Override
    public Repository getByPK(String id) {
        SingleHandler<Repository> handler = session -> session.load(Repository.class, Integer.parseInt(id));
        return sessionInTransaction.getById(handler);
    }
}
