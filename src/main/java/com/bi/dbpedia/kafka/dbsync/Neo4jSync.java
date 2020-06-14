package com.bi.dbpedia.kafka.dbsync;

import com.bi.dbpedia.model.DataTable;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Qualifier("neo4j")
public class Neo4jSync implements AbstractSync {

    private Session neo4jTemplate;

    @Autowired
    public Neo4jSync(Driver neo4jDriver){
        neo4jTemplate = neo4jDriver.session();
    }

    @Override
    public void update(List<DataTable> newList, List<Map<String, String>> oldList) {
        // 更新
        System.out.println("neo4j update");
    }

    @Override
    public void delete(List<DataTable> deleteList) {
        // 删除
        System.out.println("neo4j delete");
    }

    @Override
    public void insert(List<DataTable> insertList) {
        // 插入
        System.out.println("neo4j insert");
    }
}
