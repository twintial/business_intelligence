package com.bi.dbpedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DbpediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbpediaApplication.class, args);
    }
}
