package org.educama;

import java.io.IOException;

import javax.sql.DataSource;

import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.camunda.bpm.engine.spring.ProcessEngineFactoryBean;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.educama.shipment.cmmn.sentries.ShipmentOrderCompletedSentry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class InMemProcessEngineConfiguration {

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public ProcessEngineConfiguration processEngineConfiguration() {
      return new StandaloneInMemProcessEngineConfiguration().setJobExecutorDeploymentAware(true);
    }

    @Bean
    public ProcessEngineFactoryBean processEngine() throws Exception {
      ProcessEngineFactoryBean engineFactoryBean = new ProcessEngineFactoryBean();
      engineFactoryBean.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration());

      return engineFactoryBean;
    }
    
//    
//    @Bean
//    public DataSource dataSource() {
//        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
//        dataSource.setDriverClass(org.h2.Driver.class);
//        dataSource.setUrl("jdbc:h2:mem:camunda-test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
//        dataSource.setUsername("sa");
//        dataSource.setPassword("");
//        return dataSource;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(dataSource());
//    }
//
//    @Bean
//    public SpringProcessEngineConfiguration processEngineConfiguration() throws IOException {
//        SpringProcessEngineConfiguration config = new SpringProcessEngineConfiguration();
//        config.setTransactionManager(transactionManager());
//        config.setDataSource(dataSource());
//        config.setDatabaseSchemaUpdate("true");
//        config.setJobExecutorActivate(false);
//        return config;
//
//    }
//    
//    @Bean 
//    public StandaloneInMemProcessEngineConfiguration processEngineConfiguration() {
//        System.out.println("HOLGER");
//        StandaloneInMemProcessEngineConfiguration config = new StandaloneInMemProcessEngineConfiguration();
//        config.setDatabaseSchemaUpdate("true");
//        return config;
//        
//    }
//    
//    @Bean
//    public ProcessEngineFactoryBean processEngine() throws IOException {
//        ProcessEngineFactoryBean factoryBean = new ProcessEngineFactoryBean();
//        factoryBean.setProcessEngineConfiguration(processEngineConfiguration());
//        return factoryBean;
//
//    }
//
    @Bean
    public ProcessEngineRule processEngineRule() {
        return new ProcessEngineRule();
    }
    
    @Bean
    public ShipmentOrderCompletedSentry shipmentOrderCompletedSentry() {
        return new ShipmentOrderCompletedSentry();
    }

}
