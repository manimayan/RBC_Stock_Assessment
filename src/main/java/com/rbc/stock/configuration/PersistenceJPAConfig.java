package com.rbc.stock.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Manimaran Palani
 * @since 16-Feb-2023
 */

@Configuration
@EnableTransactionManagement
public class PersistenceJPAConfig {

    @Value("${jdbc.driver}")
    private String jdbcDriver;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.user}")
    private String jdbcUser;
    @Value("${jdbc.password}")
    private String jdbcPassword;
    @Value("${hibernate.showSql}")
    private boolean hibernateShowSql;
    @Value("${rbc.database.platform}")
    private String databasePlatform;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource());
        entityManager.setPersistenceUnitName("mysqlServer");
        entityManager.setPackagesToScan("com.rbc.stock.model");
        entityManager.setJpaVendorAdapter(vendorAdapter());
        entityManager.setJpaProperties(additionalProperties());
        return entityManager;
    }

    @Bean
    public JpaVendorAdapter vendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(hibernateShowSql);
        adapter.setDatabasePlatform(databasePlatform);
        adapter.setDatabase(Database.MYSQL);
        return adapter;
    }

    public DataSource dataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(jdbcDriver);
        dataSourceBuilder.url(jdbcUrl);
        dataSourceBuilder.username(jdbcUser);
        dataSourceBuilder.password(jdbcPassword);
        return dataSourceBuilder.build();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.id.new_generator_mappings", "false");
        properties.setProperty("hibernate.generate_statistics", "false");
        properties.setProperty("hibernate.dialect", databasePlatform);
        return properties;
    }
}
