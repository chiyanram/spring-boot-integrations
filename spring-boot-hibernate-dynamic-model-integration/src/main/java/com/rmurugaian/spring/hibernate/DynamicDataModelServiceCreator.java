package com.rmurugaian.spring.hibernate;

import com.rmurugaian.spring.hibernate.service.DynamicDataModelService;
import com.rmurugaian.spring.hibernate.service.HibernateDynamicDataModelService;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Log4j2
public class DynamicDataModelServiceCreator {

    private final DataSource dataSource;

    public DynamicDataModelServiceCreator(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    @SneakyThrows
    public DynamicDataModelService dynamicDataModelService() {

        final Properties properties = new Properties();
        properties.put("hibernate.default_entity_mode", "dynamic-map");
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.connection.provider_disables_autocommit", "true");

        final LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setHibernateProperties(properties);
        localSessionFactoryBean.setMappingResources("entity.hbm.xml", "author.hbm.xml", "account.hbm.xml");

        localSessionFactoryBean.afterPropertiesSet();
        final SessionFactory sessionFactory = localSessionFactoryBean.getObject();
        log.info("Session factory created {} ", sessionFactory);

        final HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory);

        return new HibernateDynamicDataModelService(sessionFactory, hibernateTransactionManager);
    }

}
