package hibernate.dao;

import hibernate.DaoAccessor;
import hibernate.entity.Repository;
import hibernate.entity.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by User on 06.04.2016.
 */
public class RepositoryDaoTest {

    @Test
    public void testGetAll() throws Exception {
        List<Repository> list = DaoAccessor.getInstance().getRepositoryDAO().getAll();
        assertNotNull(list);
        assertNotNull(list.size() > 0);
        System.out.println(list.get(0).getLogin_adm().getLogin());
    }

    @Test
    public void testGetById() throws Exception {
        Repository repository = DaoAccessor.getInstance().getRepositoryDAO().getById("1");
        assertNotNull(repository);
        System.out.println(repository.getAdress() + " " + repository.getTitle_news().getTitle_news());
        assertTrue(repository.getAdress() == 1);
    }


}