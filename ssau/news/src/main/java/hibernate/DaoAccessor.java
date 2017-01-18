package hibernate;

import hibernate.dao.AdminDao;
import hibernate.dao.NewsDao;
import hibernate.dao.RepositoryDao;
import hibernate.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Vladimir on 04.04.2016.
 */
@Component
public class DaoAccessor {

    @Autowired
    private AdminDao adminDao;
    @Autowired
    private NewsDao newsDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RepositoryDao repositoryDao;

    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public void setRepositoryDao(RepositoryDao repositoryDao) {
        this.repositoryDao = repositoryDao;
    }

    public void setNewsDao(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public AdminDao getAdminDao() {
        return adminDao;
    }

    public NewsDao getNewsDao() {
        return newsDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public RepositoryDao getRepositoryDao() {
        return repositoryDao;
    }
}
