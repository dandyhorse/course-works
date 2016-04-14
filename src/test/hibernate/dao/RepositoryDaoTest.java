package hibernate.dao;

import hibernate.entity.Repository;
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
        Repository repository = daoAccessor.getRepositoryDao().getByPK("0");
        assertNotNull(repository);
        System.out.println(repository.getId() + " " + repository.getNews().getTitle());
        assertTrue(repository.getId() == 0);
    }


}