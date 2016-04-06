package hibernate;

import hibernate.dao.AdminDao;
import hibernate.dao.NewsDao;
import hibernate.dao.RepositoryDao;
import hibernate.dao.UserDao;

/**
 * Created by Vladimir on 04.04.2016.
 */
public class DaoAccessor {

    private static volatile DaoAccessor instance;

    public static DaoAccessor getInstance() {
        if (instance == null) {
            instance = new DaoAccessor();
        }
        return instance;
    }

    public AdminDao getAdminDao() {
        return new AdminDao();
    }

    public NewsDao getNewsDao() {
        return new NewsDao();
    }

    public UserDao getUserDao() {
        return new UserDao();
    }

    public RepositoryDao getRepositoryDAO() {
        return new RepositoryDao();
    }
}
