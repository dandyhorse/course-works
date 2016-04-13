package hibernate.dao;

import hibernate.DaoAccessor;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by User on 14.04.2016.
 */
public abstract class AbsTest {
    private static ApplicationContext applicationContext;
    protected DaoAccessor daoAccessor;

    static {
        applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
    }

    @Before
    public void setUp() throws Exception {
        daoAccessor = applicationContext.getBean(DaoAccessor.class);
    }
}
