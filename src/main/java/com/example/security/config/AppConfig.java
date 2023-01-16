package com.example.security.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.example.security")
@PropertySource("classpath:persistence-mysql.properties")
public class AppConfig {

    // set up Class to hold our properties
    private final Environment environment;

    // set up Logger
    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public AppConfig(Environment environment) {
        this.environment = environment;
    }


    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();

        internalResourceViewResolver.setPrefix("/WEB-INF/view/");
        internalResourceViewResolver.setSuffix(".jsp");

        return internalResourceViewResolver;
    }

    //define a bean for our security datasource
    @Bean
    public DataSource securityDataSource() {

        // create connection pool
        ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

        // set jdbc driver class
        try {
            securityDataSource.setDriverClass(environment.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        // log the connection props
        logger.info("****************** jdbc.url=" + environment.getProperty("jdbc.url"));
        logger.info("****************** jdbc.user=" + environment.getProperty("jdbc.user"));

        // set connection pool props
        securityDataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        securityDataSource.setUser(environment.getProperty("jdbc.user"));
        securityDataSource.setPassword(environment.getProperty("jdbc.password"));
        securityDataSource.setInitialPoolSize(propAsInt(environment.getProperty("connection.pool.initialPoolSize")));
        securityDataSource.setMinPoolSize(propAsInt(environment.getProperty("connection.pool.minPoolSize")));
        securityDataSource.setMaxPoolSize(propAsInt(environment.getProperty("connection.pool.maxPoolSize")));
        securityDataSource.setMaxIdleTime(propAsInt(environment.getProperty("connection.pool.maxIdleTime")));

        return securityDataSource;
    }

    private int propAsInt(String prop) {
        return Integer.parseInt(prop);
    }

}
