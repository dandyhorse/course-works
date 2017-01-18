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
        CollectionHandler<News> handler = session -> {
            Criteria criteria = session.createCriteria(News.class);
            return criteria.list();
        };
        return sessionInTransaction.getAll(handler);
    }

    @Override
    public News getByPK(String id) {
        SingleHandler<News> handler = session -> session.load(News.class, Integer.parseInt(id));
        return sessionInTransaction.getById(handler);
    }
}
