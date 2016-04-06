package hibernate.dao;

import hibernate.entity.News;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Vladimir on 04.04.2016.
 */
public class NewsDao extends AbstractDao<News> {
    private final String sqlQuery = "select * from news";

    @Override
    public List<News> getAll() {
        SessionInTransaction sessionInTransaction = new SessionInTransaction();
        return sessionInTransaction.getAll(new ListHandler() {
            @Override
            public List<News> handle(Session session) {
                return session.createSQLQuery(sqlQuery).addEntity(News.class).list();
            }
        });
    }

    @Override
    public News getById(String i) {
        SessionInTransaction sessionInTransaction = new SessionInTransaction();
        return sessionInTransaction.getById(new SingleHandler() {
            @Override
            public News handle(Session session) {
                return session.load(News.class, i);
            }
        });
    }
}
