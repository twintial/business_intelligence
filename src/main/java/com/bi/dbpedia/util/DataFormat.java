package com.bi.dbpedia.util;

import com.bi.dbpedia.model.Resource;
import com.bi.dbpedia.model.GraphData;
import com.bi.dbpedia.model.Link;
import org.neo4j.driver.Record;
import org.neo4j.driver.Value;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Path;
import org.neo4j.driver.types.Relationship;

import java.util.*;

public class DataFormat {

    public static GraphData CovertRecordToData(List<Record> records){
        Set<Resource> entityList = new HashSet<>();
        Set<Link> linkList = new HashSet<>();
        for (Record record : records) {
            for (Value value : record.values()) {
                Path path = value.asPath();
                Iterable<Node> nodes = path.nodes();
                Iterable<Relationship> relationships = path.relationships();

                for (Node node : nodes) {
                    Map<String, Object> map = node.asMap();
                    entityList.add(
                            new Resource(node.id(),
                            map.get("name").toString(),
                            map.get("uri").toString(), map.get("label").toString()));
                }

                for (Relationship relationship : relationships) {
                    Map<String, Object> map = relationship.asMap();
                    linkList.add(new Link(relationship.startNodeId(), relationship.endNodeId(),
                            map.get("name").toString(), map.get("uri").toString(), map.get("label").toString()));
                }
            }
        }
        return new GraphData(entityList, linkList);
    }
}
