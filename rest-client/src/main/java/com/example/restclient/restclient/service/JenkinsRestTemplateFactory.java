package com.example.restclient.restclient.service;

import org.apache.hc.core5.http.HttpHost;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class JenkinsRestTemplateFactory implements FactoryBean<RestTemplate>, InitializingBean {

    private RestTemplate restTemplate;

    public RestTemplate getObject() {
        return restTemplate;
    }

    public Class<RestTemplate> getObjectType() {
        return RestTemplate.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() {
        URI uri = new URI();
        HttpHost host = new HttpHost("localhost", 8082, "http");
        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactoryBasicAuth(host));
    }
}