package com.bi.dbpedia.util;

import com.bi.dbpedia.model.Entity;
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
        Set<Entity> entityList = new HashSet<>();
        Set<Link> linkList = new HashSet<>();
        for (Record record : records) {
            for (Value value : record.values()) {
                Path path = value.asPath();
                Iterable<Node> nodes = path.nodes();
                Iterable<Relationship> relationships = path.relationships();

                for (Node node : nodes) {
                    Map<String, Object> map = node.asMap();
                    // 暂时这样写，等有数据了改
                    entityList.add(new Entity(node.id(), "1", "1", Collections.singletonList("1")));
                }

                for (Relationship relationship : relationships) {
                    linkList.add(new Link(relationship.startNodeId(), relationship.endNodeId(), relationship.type()));
                }
            }
        }
        return new GraphData(entityList, linkList);
    }
}
