package com.epam.memorina.initializer;

import com.epam.memorina.configs.SpringJpaConfig;
import com.epam.memorina.configs.SpringWebConfig;
import org.springframework.context.annotation.Import;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author Solovev Anton
 * @since 21.07.2016.
 */
@Import(SpringJpaConfig.class)
public class AppInitializer implements WebApplicationInitializer {

    private static final Class MAIN_CONFIG_CLASS = SpringWebConfig.class;
    private static final String MAPPING_URL = "/";

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        WebApplicationContext context = getContext();
        container.addListener(new ContextLoaderListener(context));
        ServletRegistration.Dynamic dispatcher = container.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping(MAPPING_URL);
    }

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(MAIN_CONFIG_CLASS);
        return context;
    }

}
