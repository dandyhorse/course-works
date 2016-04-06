package hibernate.dao;

import hibernate.entity.Repository;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Vladimir on 04.04.2016.
 */
public class RepositoryDao extends AbstractDao<Repository>{
    private final String sqlQuery = "select * from repository";

    @Override
    public List<Repository> getAll() {
        SessionInTransaction sessionInTransaction = new SessionInTransaction();
        return sessionInTransaction.getAll(new ListHandler(){
            @Override
            public List<Repository> handle(Session session){
                return session.createSQLQuery(sqlQuery).addEntity(Repository.class).list();
            }
        });
    }

    @Override
    public Repository getById(String i) {
        SessionInTransaction sessionInTransaction = new SessionInTransaction();
        return sessionInTransaction.getById(new SingleHandler() {
            @Override
            public Repository handle(Session session) {
                return session.load(Repository.class, Integer.valueOf(i));
            }
        });
    }
}
