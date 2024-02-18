package org.example.config;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.camel.spring.spi.SpringTransactionPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TransactedJmsConfig {

    @Bean("activeMqTx")
    public JmsComponent activeMqTx() {
        JmsComponent activeMqTx = new JmsComponent();
        activeMqTx.setConfiguration(activeMqTxConfig());
        return activeMqTx;
    }

    @Bean("activeMqTxConfig")
    public JmsConfiguration activeMqTxConfig() {
        JmsConfiguration activeMqTxConfig = new JmsConfiguration();
        activeMqTxConfig.setConnectionFactory(cachingConnectionFactory());
        activeMqTxConfig.setTransactionManager(activeMqTransactionManager());
        activeMqTxConfig.setTransacted(true);
        activeMqTxConfig.setLazyCreateTransactionManager(false);
        activeMqTxConfig.setDisableTimeToLive(true);
        return activeMqTxConfig;
    }

    @Bean("activeMqTransactionManager")
    public PlatformTransactionManager activeMqTransactionManager() {
        JmsTransactionManager activeMqTransactionManager = new JmsTransactionManager();
        activeMqTransactionManager.setConnectionFactory(cachingConnectionFactory());
        return activeMqTransactionManager;
    }

    @Bean("cachingConnectionFactory")
    public CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setTargetConnectionFactory(activeMqConnectionFactory());
        return cachingConnectionFactory;
    }

    @Bean("activeMqConnectionFactory")
    public ActiveMQConnectionFactory activeMqConnectionFactory() {
        ActiveMQConnectionFactory activeMqConnectionFactory = new ActiveMQConnectionFactory();
        activeMqConnectionFactory.setBrokerURL("tcp://localhost:61616");
        return activeMqConnectionFactory;
    }

    @Bean("PROPAGATION_REQUIRES_NEW")
    public SpringTransactionPolicy PROPAGATION_REQUIRES_NEW() {
        SpringTransactionPolicy PROPAGATION_REQUIRES_NEW = new SpringTransactionPolicy();
        PROPAGATION_REQUIRES_NEW.setTransactionManager(activeMqTransactionManager());
        PROPAGATION_REQUIRES_NEW.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        return PROPAGATION_REQUIRES_NEW;
    }

}
