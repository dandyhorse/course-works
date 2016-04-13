package hibernate.dao;

import hibernate.DaoAccessor;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by User on 14.04.2016.
 * Класс создан для предотращения постойнной загрузки новых контейнеров
 */
public class AbsTest {

    private static ApplicationContext applicationContext;
    protected static DaoAccessor daoAccessor;

    static {
        applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
    }

    @Before
    public void setUp() throws Exception {
        daoAccessor = applicationContext.getBean(DaoAccessor.class);
    }
}
