package com.bi.dbpedia.dao;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class Neo4jRepository {

    @Autowired
    private Driver neo4jDriver;

    public List<Record> queryWithCyber(String cyber, Map<String, Object> params) {
        Session neo4jTemplate = neo4jDriver.session();
        Result result = neo4jTemplate.run(cyber, params);
        return result.list();
    }

    public List<Record> queryOneEntityAndRelations(String name) {
        String cyber = "match p=(n:Resource{uri:$name})-[]-(m) return p";
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return queryWithCyber(cyber, params);
    }
}
