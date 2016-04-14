package hibernate.dao;

import hibernate.entity.Admin;
import hibernate.entity.User;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.junit.Assert.*;

/**
 * Created by User on 14.04.2016.
 */
public class AbstractDaoTest extends AbsTest {

    /**
     * @throws Exception testing add admin and user
     *                   with encryption
     *                   login use as a password
     */
    @Test
    public void testAdd() throws Exception {
        String login = "test";
        String hash = BCrypt.hashpw(login, BCrypt.gensalt());

        User user = new User(login, hash);
        Admin admin = new Admin(login, hash);

        daoAccessor.getAdminDao().add(admin);
        daoAccessor.getUserDao().add(user);

        Admin adminPK = daoAccessor.getAdminDao().getByPK(login);
        User userPK = daoAccessor.getUserDao().getByPK(login);

        assertTrue(BCrypt.checkpw(login, adminPK.getPassword()));
        assertTrue(BCrypt.checkpw(login, userPK.getPassword()));
        System.out.println(adminPK.getPassword());
        System.out.println(userPK.getPassword());

        daoAccessor.getAdminDao().delete(adminPK);
        daoAccessor.getUserDao().delete(userPK);
    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }
}