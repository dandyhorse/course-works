package hibernate.dao;

import hibernate.entity.News;
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
public class NewsDao extends AbstractDao<News> {

    @Override
    public List<News> getAll() {
        return sessionInTransaction.getAll(new CollectionHandler() {
            @Override
            public List<News> handle(Session session) {
                Criteria criteria = session.createCriteria(News.class);
                return criteria.list();
            }
        });
    }

    @Override
    public News getById(String i) {
        return sessionInTransaction.getById(new SingleHandler() {
            @Override
            public News handle(Session session) {
                return session.load(News.class, i);
            }
        });
    }
}
