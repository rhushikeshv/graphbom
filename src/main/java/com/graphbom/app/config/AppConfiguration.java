package com.graphbom.app.config;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphbom.app.model.Views;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(
        basePackages = "com.graphbom.app.repository")
@EnableTransactionManagement
public class AppConfiguration {

    @Value("${spring.node.neo4j.uri}")
    private String url;

    @Value("${spring.node.neo4j.username}")
    private String username;

    @Value("${spring.node.neo4j.password}")
    private String password;

    @Bean
    public SessionFactory sessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory(configuration(),"com.graphbom.app.model");
    }

    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {


        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder()
                .uri(url)
                .credentials(username,password)
                .build();
        return configuration;
    }

    @Bean
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        mapper.writerWithView(Views.Public.class);
        return mapper;
    }

}