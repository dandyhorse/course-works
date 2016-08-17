package hello.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
@EnableAutoConfiguration
public class ContainerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContainerApplication.class, args);
    }

    @Bean
    public HelloPojo helloPojo() {
        return new HelloPojo();
    }

    @Bean(name = "computation")
    public ComputationComponent computationComponent() {
        return new ComputationComponent();
    }

}
