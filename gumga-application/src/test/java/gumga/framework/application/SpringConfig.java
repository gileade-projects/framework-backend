package gumga.framework.application;

import gumga.framework.application.service.GumgaFreemarkerTemplateEngineService;
import gumga.framework.application.service.JasyptGumgaPasswordService;
import gumga.framework.core.GumgaValues;
import gumga.framework.core.exception.TemplateEngineException;
import gumga.framework.core.service.GumgaPasswordService;
import gumga.framework.domain.GumgaQueryParserProvider;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"gumga.framework"})
@EnableJpaRepositories(repositoryFactoryBeanClass = GumgaRepositoryFactoryBean.class, basePackages = {"gumga.framework"})
@EnableTransactionManagement(proxyTargetClass = true)
public class SpringConfig {

    @Bean
    public static DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(H2).build();
    }

    @Bean
    @Autowired
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        GumgaQueryParserProvider.defaultMap = GumgaQueryParserProvider.getOracleLikeMap();

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(false);

        Properties properties = new Properties();
        properties.put("eclipselink.weaving", "false");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("gumga");
        factory.setDataSource(dataSource);

        factory.setJpaProperties(properties);
        factory.afterPropertiesSet();

        return factory;
    }

    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public GumgaPasswordService gumgaPasswordService() {
        return new JasyptGumgaPasswordService();
    }

    @Bean
    @Autowired
    public GumgaFreemarkerTemplateEngineService gumgaFreemarkerTemplateEngineService(GumgaValues gumgaValues) throws TemplateEngineException {
        GumgaFreemarkerTemplateEngineService service = new GumgaFreemarkerTemplateEngineService(gumgaValues.getTemplatesFolder(), "UTF-8");
        service.init();
        return service;
    }
}
