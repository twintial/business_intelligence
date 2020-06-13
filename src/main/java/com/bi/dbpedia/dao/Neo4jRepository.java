package com.bi.dbpedia.dao;

import com.bi.dbpedia.dto.Neo4jQueryParam;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class Neo4jRepository {

    private Session neo4jTemplate;

    @Autowired
    public Neo4jRepository(Driver neo4jDriver){
        neo4jTemplate = neo4jDriver.session();
    }

    public List<Record> queryWithCyber(String cyber, Map<String, Object> params) {
        Result result = neo4jTemplate.run(cyber, params);
        return result.list();
    }

    public List<Record> queryOneEntityAndRelations(String name) {
        // 无视大小写
        name = "(?i)" + name;
        // 可以考虑将cyber语句放入一个文件内或者数据库内
        String cyber = "match p=(n:Resource)-[]-(m:Resource) where n.name=~$name return p";
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return queryWithCyber(cyber, params);
    }

    public List<Record> queryTwoEntityWithNLink(String name1, String name2, int n) {
        name1 = "(?i)" + name1;
        name2 = "(?i)" + name2;
        // 可以考虑将cyber语句放入一个文件内或者数据库内
        String cyber = String.format("match p=(n:Resource)-[*1..%d]-(m:Resource) where n.name=~$name1 and m.name=~$name2 return p", n);
        Map<String, Object> params = new HashMap<>();
        params.put("name1", name1);
        params.put("name2", name2);
        return queryWithCyber(cyber, params);
    }

    public List<Record> basicQuery(Neo4jQueryParam param) {
        return null;
    }
}
