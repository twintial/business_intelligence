package com.bi.dbpedia.model.elasticsearch;

import org.neo4j.ogm.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "user", refreshInterval = "30s")
public class User {

    @Id
    private String id;
    private String age;
    private String city;
    private String description;
    private String name;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", age='" + age + '\'' +
                ", city='" + city + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
