package hibernate.dao;

import hibernate.DaoAccessor;
import hibernate.entity.User;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by User on 06.04.2016.
 */
public class UserDaoTest {

    @Test
    public void testGetById() throws Exception {
        Integer sd = DaoAccessor.getInstance().getUserDao().getById("sd").getPassword();
        System.out.println(sd);
        assertTrue(sd == 234);
    }

    @Test
    public void testGetAll() throws Exception {
        List<User> all = DaoAccessor.getInstance().getUserDao().getAll();
        assertNotNull(all);
        System.out.println(all.get(0).getLogin());
        assertNotNull(all.get(0));
    }
}