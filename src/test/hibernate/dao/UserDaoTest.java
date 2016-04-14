package hibernate.dao;

import hibernate.entity.User;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by User on 06.04.2016.
 */
public class UserDaoTest extends AbsTest {

    @Test
    public void testGetById() throws Exception {
        String login = "first-user";
        User user = daoAccessor.getUserDao().getByPK(login);
        assertTrue(user.getLogin().equals(login));
        System.out.println(user.getLogin());
    }

    @Test
    public void testGetAll() throws Exception {
        List<User> all = daoAccessor.getUserDao().getAll();
        assertNotNull(all);
        System.out.println(all.get(0).getLogin());
        assertNotNull(all.get(0));
    }
}