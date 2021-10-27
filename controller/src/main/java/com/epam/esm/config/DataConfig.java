package com.epam.esm.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@PropertySource(value = "classpath:application.properties")
@ComponentScan("com.epam.esm.*")
public class DataConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    @Autowired
    public DataConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Value("${driverClassName}")
    private String driverClassName;
    @Value("${jdbcUrl}")
    private String jdbcUrl;
    @Value("${dataSource.user}")
    private String userName;
    @Value("${dataSource.password}")
    private String password;
    @Value("${dataSource.maximumPoolSize}")
    private int maxPoolSize;
    @Value("${dataSource.poolName}")
    private String poolName;

    @Bean
    public DataSource dataSource() {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(userName);
        config.setPassword(password);
        config.setDriverClassName(driverClassName);
        config.setMaximumPoolSize(maxPoolSize);
        config.setPoolName(poolName);
        return new HikariDataSource(config);

    }

    @Bean
    public JdbcTemplate jdbcTemplate() {

        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSourceTransactionManager createTransactionManager() {

        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

}

