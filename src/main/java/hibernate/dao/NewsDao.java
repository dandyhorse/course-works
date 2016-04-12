package hibernate.dao;

import hibernate.entity.News;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Vladimir on 04.04.2016.
 */
public class NewsDao extends AbstractDao<News> {

    @Override
    public List<News> getAll() {
        inTransaction = new SessionInTransaction();
        return inTransaction.getAll(new CollectionHandler() {
            @Override
            public List<News> handle(Session session) {
                Criteria criteria = session.createCriteria(News.class);
                return criteria.list();
            }
        });
    }

    @Override
    public News getById(String i) {
        inTransaction = new SessionInTransaction();
        return inTransaction.getById(new SingleHandler() {
            @Override
            public News handle(Session session) {
                return session.load(News.class, i);
            }
        });
    }
}
