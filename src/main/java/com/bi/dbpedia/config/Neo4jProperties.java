package com.bi.dbpedia.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.data.neo4j")
public class Neo4jProperties {

    private String uri;
    private String username;
    private String password;
}
