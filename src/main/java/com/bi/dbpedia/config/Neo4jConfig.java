package com.bi.dbpedia.config;

import org.neo4j.driver.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties(Neo4jProperties.class)
public class Neo4jConfig {

    private final Neo4jProperties neo4jProperties;

    public Neo4jConfig(Neo4jProperties neo4jProperties) {
        this.neo4jProperties = neo4jProperties;
    }

    @Bean
    @Qualifier("neo4jDriver")
    public Driver driver() {
        Config config = Config.builder().withMaxConnectionPoolSize(50)
                .withConnectionAcquisitionTimeout(5, TimeUnit.SECONDS).build();
        return GraphDatabase.driver(neo4jProperties.getUri(),
                AuthTokens.basic(neo4jProperties.getUsername(), neo4jProperties.getPassword()),
                config);
    }
}
