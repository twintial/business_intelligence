package com.bi.dbpedia.kafka.dbsync;

import com.bi.dbpedia.model.DataTable;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
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
        for (int i=0;i<oldList.size();i++) {

            String cypher = "match (n:Resource{name:$name0})" +
                    "<-[r:Relation]-"+
                    "(m:Resource{name:$name00})"+
                    "set n.name=$name1 set n.label=$label1 set n.uri=$uri1"+
                    "set m.name=$name2 set m.label=$label2 set m.uri=$uri2"+
                    "set r.name=$name3 set r.label=$label3 set r.uri=$uri3";
            Map<String, Object> params = new HashMap<>();
            params.put("name1", newList.get(i).getObject());
            params.put("uri1", newList.get(i).getObjectUri());
            params.put("label1", newList.get(i).getObjectLabel());
            params.put("name2", newList.get(i).getSubject());
            params.put("uri2", newList.get(i).getSubjectUri());
            params.put("label2", newList.get(i).getSubjectLabel());
            params.put("name3", newList.get(i).getPredicate());
            params.put("uri3", newList.get(i).getPredicateUri());
            params.put("label3", newList.get(i).getPredicateLabel());
            params.put("name0",oldList.get(i).get("object"));
            params.put("name00",oldList.get(i).get("subject"));
            neo4jTemplate.run(cypher, params);
            System.out.println("neo4j update");
        }
    }

    @Override
    public void delete(List<DataTable> deleteList) {
        // 删除
        for (DataTable dataTable : deleteList) {
            String cypher = "match (n:Resource{name:$name1,uri:$uri1,label:$label1})" +
                    "<-[r:Relation{name:$name3,uri:$uri3,label:$label3}]-"+
                    "(m:Resource{name:$name2,uri:$uri2,label:$label2}) delete r";
            Map<String, Object> params = new HashMap<>();
            params.put("name1", dataTable.getObject());
            params.put("uri1", dataTable.getObjectUri());
            params.put("label1", dataTable.getObjectLabel());
            params.put("name2", dataTable.getSubject());
            params.put("uri2", dataTable.getSubjectUri());
            params.put("label2", dataTable.getSubjectLabel());
            params.put("name3", dataTable.getPredicate());
            params.put("uri3", dataTable.getPredicateUri());
            params.put("label3", dataTable.getPredicateLabel());
            neo4jTemplate.run(cypher, params);
            System.out.println("neo4j delete");
        }
    }

    @Override
    public void insert(List<DataTable> insertList) {
        // 插入
        for (DataTable dataTable : insertList) {
            String cypher = "merge (n:Resource{name:$name1,uri:$uri1,label:$label1}) " +
                    "merge(m:Resource{name:$name2,uri:$uri2,label:$label2}) " +
                    "merge (n)-[l:Relation{name:$name3,uri:$uri3,label:$label3}]->(m) ";
            Map<String, Object> params = new HashMap<>();
            params.put("name2", dataTable.getObject());
            params.put("uri2", dataTable.getObjectUri());
            params.put("label2", dataTable.getObjectLabel());
            params.put("name1", dataTable.getSubject());
            params.put("uri1", dataTable.getSubjectUri());
            params.put("label1", dataTable.getSubjectLabel());
            params.put("name3", dataTable.getPredicate());
            params.put("uri3", dataTable.getPredicateUri());
            params.put("label3", dataTable.getPredicateLabel());
            neo4jTemplate.run(cypher, params);
            System.out.println("neo4j insert");
        }
    }
}
