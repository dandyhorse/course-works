package com.epam.memorina.configs;

/**
 * @author Solovev Anton
 * @since 01.08.2016.
 */

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.epam.memorina.assemblers"})
public class SpringAsmConfig {
}
