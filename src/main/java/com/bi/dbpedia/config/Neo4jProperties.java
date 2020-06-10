package com.bi.dbpedia.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.data.neo4j")
public class Neo4jProperties {

    private String uri;
    private String username;
    private String password;
}
