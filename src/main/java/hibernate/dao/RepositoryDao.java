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
        return sessionInTransaction.getAll(new CollectionHandler(){
            @Override
            public List<Repository> handle(Session session){
                Criteria criteria = session.createCriteria(Repository.class);
                return criteria.list();
            }
        });
    }

    @Override
    public Repository getById(String i) {
        return sessionInTransaction.getById(new SingleHandler() {
            @Override
            public Repository handle(Session session) {
                return session.load(Repository.class, Integer.parseInt(i));
            }
        });
    }
}
