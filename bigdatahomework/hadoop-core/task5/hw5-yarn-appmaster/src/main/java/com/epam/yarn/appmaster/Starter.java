package com.epam.yarn.appmaster;

import com.epam.yarn.web.EmbeddedServer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

/**
 * @author Anton_Solovev
 * @since 10/6/16.
 */
@ComponentScan("com.epam.yarn")
@EnableAutoConfiguration
public class Starter {
    private static final Log log = LogFactory.getLog(Starter.class);

    public static void main(String[] args) {
        log.debug("starting com.epam.web with arguments: " + Arrays.asList(args));
        SpringApplication.run(new Object[]{Starter.class, EmbeddedServer.class}, args);
    }

}
