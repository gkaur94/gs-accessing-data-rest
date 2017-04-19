package hello;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories("hello")
public class RdsConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url(System.getenv("SPRING_DATASOURCE_URL"))
                .username(System.getenv("SPRING_DATASOURCE_USERNAME"))
                .password(System.getenv("SPRING_DATASOUCE_PASSWORD"))
                .build();
    }
}