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

            DataTable data = newList.get(i);
            Map<String, String> oldMap = oldList.get(i);
            // 更新object
            String objectId = oldMap.get("object") != null ? oldMap.get("object"): data.getObject();
            // 更新subject
            String subjectId = oldMap.get("subject") != null ? oldMap.get("subject"): data.getSubject();
            // 更新predicate
            String predicateId = oldMap.get("predicate") != null ? oldMap.get("predicate"): data.getPredicate();

            String cypher = "match (n:Resource{name:$o_name})" +
                    "<-[r:Relation{name:$l_name}]-"+
                    "(m:Resource{name:$s_name})"+
                    " set n.name=$name1 set n.label=$label1 set n.uri=$uri1"+
                    " set m.name=$name2 set m.label=$label2 set m.uri=$uri2"+
                    " set r.name=$name3 set r.label=$label3 set r.uri=$uri3";
            Map<String, Object> params = new HashMap<>();
            params.put("name1", data.getObject());
            params.put("uri1", data.getObjectUri());
            params.put("label1", data.getObjectLabel());
            params.put("name2", data.getSubject());
            params.put("uri2", data.getSubjectUri());
            params.put("label2", data.getSubjectLabel());
            params.put("name3", data.getPredicate());
            params.put("uri3", data.getPredicateUri());
            params.put("label3", data.getPredicateLabel());
            params.put("o_name",objectId);
            params.put("s_name",subjectId);
            params.put("l_name", predicateId);
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
        }
        System.out.println("neo4j delete");
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
