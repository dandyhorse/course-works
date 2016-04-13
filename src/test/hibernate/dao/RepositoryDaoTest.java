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
public class RepositoryDaoTest extends AbsTest{

    @Test
    public void testGetAll() throws Exception {
        List<Repository> list = daoAccessor.getRepositoryDao().getAll();
        assertNotNull(list);
        assertNotNull(list.size() > 0);
        System.out.println(list.get(0).getAdmin().getLogin());
    }

    @Test
    public void testGetById() throws Exception {
        Repository repository = daoAccessor.getRepositoryDao().getById("1");
        assertNotNull(repository);
        System.out.println(repository.getAddress() + " " + repository.getNews().getTitle());
        assertTrue(repository.getAddress() == 1);
    }


}