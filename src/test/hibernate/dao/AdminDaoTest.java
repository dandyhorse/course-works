package hibernate.dao;

import hibernate.DaoAccessor;
import hibernate.entity.Admin;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by User on 06.04.2016.
 */
public class AdminDaoTest {

    @Test
    public void testGetAll() throws Exception {
        List<Admin> list = DaoAccessor.getInstance().getAdminDao().getAll();
        assertNotNull(list);
        assertTrue(list.size() > 0);
        System.out.println(list.get(0).getLogin());
    }

    @Test
    public void testGetById() throws Exception {
        Admin admin = DaoAccessor.getInstance().getAdminDao().getById("root");
        assertNotNull(admin);
        assertTrue(admin.getPassword() == 4321);
        System.out.println(admin.getLogin());
    }
}