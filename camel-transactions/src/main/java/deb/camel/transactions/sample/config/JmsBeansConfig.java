package deb.camel.transactions.sample.config;

import jakarta.jms.ConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.camel.spring.spi.SpringTransactionPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.support.destination.JndiDestinationResolver;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;

import java.util.Properties;

@Configuration("jmsBeansConfig")
public class JmsBeansConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(JmsBeansConfig.class);

    private static final String solaceUrl = "";
    private static final String solacePrincipal = "";
    private static final String solaceCredentials = "";
    private static final String solaceJndiName = "";
    private static final int solaceSessionCacheSize = 10;

    private static final String sslTrustStore = "";
    private static final String sslTrustStorePassword = "";
    private static final String sslKeyStore = "";
    private static final String sslKeyStorePassword = "";

    @Bean(name = "systemProps")
    public MethodInvokingFactoryBean systemProps(@Value("#{systemProperties}") Object systemProperties) {
        MethodInvokingFactoryBean systemProps = new MethodInvokingFactoryBean();
        systemProps.setTargetObject(systemProperties);
        systemProps.setTargetMethod("putAll");
        Properties arguments = new Properties();
        arguments.setProperty("javax.net.ssl.trustStore", sslTrustStore);
        arguments.setProperty("javax.net.ssl.trustStorePassword", sslTrustStorePassword);
        arguments.setProperty("javax.net.ssl.keyStore", sslKeyStore);
        arguments.setProperty("javax.net.ssl.keyStorePassword", sslKeyStorePassword);
        systemProps.setArguments(arguments);
        return systemProps;
    }

    @Bean(name = "solaceJndiTemplate")
    public JndiTemplate solaceJndiTemplate() {
        JndiTemplate solaceJndiTemplate = new JndiTemplate();
        Properties environment = new Properties();
        environment.put("java.naming.provider.url", solaceUrl);
        environment.put("java.naming.factory.initial", "com.solacesystems.jndi.SolJNDIInitialContextFactory");
        environment.put("java.naming.security.principal", solacePrincipal);
        environment.put("java.naming.security.credentials", solaceCredentials);
        solaceJndiTemplate.setEnvironment(new Properties());
        return solaceJndiTemplate;
    }

    @Bean(name = "solaceJndiDestinationResolver")
    public JndiDestinationResolver solaceJndiDestinationResolver() {
        JndiDestinationResolver solaceJndiDestinationResolver = new JndiDestinationResolver();
        solaceJndiDestinationResolver.setJndiTemplate(solaceJndiTemplate());
        solaceJndiDestinationResolver.setCache(true);
        return solaceJndiDestinationResolver;
    }

    @Bean(name = "solaceConnectionFactory")
    public JndiObjectFactoryBean solaceConnectionFactory() {
        JndiObjectFactoryBean solaceConnectionFactory = new JndiObjectFactoryBean();
        solaceConnectionFactory.setJndiTemplate(solaceJndiTemplate());
        solaceConnectionFactory.setJndiName(solaceJndiName);
        return solaceConnectionFactory;
    }

    @Bean(name = "solaceCachingConnectionFactory")
    public CachingConnectionFactory solaceCachingConnectionFactory() {
        CachingConnectionFactory solaceCachingConnectionFactory = new CachingConnectionFactory();
        solaceCachingConnectionFactory.setTargetConnectionFactory((ConnectionFactory) solaceConnectionFactory().getObject());
        solaceCachingConnectionFactory.setSessionCacheSize(solaceSessionCacheSize);
        solaceCachingConnectionFactory.setCacheConsumers(false);
        solaceCachingConnectionFactory.setCacheProducers(false);
        return solaceCachingConnectionFactory;
    }

    @Bean(name = "solaceJmsConfig")
    public JmsConfiguration solaceJmsConfig() {
        JmsConfiguration solaceJmsConfig = new JmsConfiguration();
        solaceJmsConfig.setConnectionFactory(solaceCachingConnectionFactory());
        solaceJmsConfig.setDestinationResolver(solaceJndiDestinationResolver());
        solaceJmsConfig.setTransacted(true);
        solaceJmsConfig.setLazyCreateTransactionManager(false);
        solaceJmsConfig.setDisableTimeToLive(true);
        return solaceJmsConfig;
    }

    @Bean(name = "solaceJmsComponent")
    public JmsComponent solaceJmsComponent() {
        JmsComponent solaceJmsComponent = new JmsComponent();
        solaceJmsComponent.setConfiguration(solaceJmsConfig());
        return solaceJmsComponent;
    }

    @Bean(name = "jmsTransactionManager")
    public JmsTransactionManager jmsTransactionManager() {
        JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
        jmsTransactionManager.setConnectionFactory(solaceCachingConnectionFactory());
        return jmsTransactionManager;
    }

    @Bean(name = "chainedTransactionManager")
    public ChainedTransactionManager chainedTransactionManager() {
        ChainedTransactionManager chainedTransactionManager = new ChainedTransactionManager(jmsTransactionManager());
        return chainedTransactionManager;
    }

    @Bean(name = "chainedTransactionPolicy")
    public SpringTransactionPolicy chainedTransactionPolicy() {
        SpringTransactionPolicy chainedTransactionPolicy = new SpringTransactionPolicy();
        chainedTransactionPolicy.setTransactionManager(chainedTransactionManager());
        //chainedTransactionPolicy.setPropagationBehaviorName("PROPAGATION_REQUIRED");
        return chainedTransactionPolicy;
    }

}