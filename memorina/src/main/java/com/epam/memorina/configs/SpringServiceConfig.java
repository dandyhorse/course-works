package com.epam.memorina.configs;

/**
 * @author Solovev Anton
 * @since 30.07.2016.
 */

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"com.epam.memorina.services"})
public class SpringServiceConfig {
}
