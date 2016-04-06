package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Vladimir on 04.04.2016.
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    public HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    static {
        try {
            sessionFactory = (new Configuration()).configure().buildSessionFactory();
        } catch (Throwable var1) {
            System.err.println("Initial SessionFactory creation failed." + var1);
            throw new ExceptionInInitializerError(var1);
        }
    }

}
