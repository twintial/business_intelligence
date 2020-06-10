package com.bi.dbpedia.service.impl;

import com.bi.dbpedia.dao.Neo4jRepository;
import com.bi.dbpedia.model.Entity;
import com.bi.dbpedia.model.GraphData;
import com.bi.dbpedia.model.Link;
import com.bi.dbpedia.service.BasicQueryService;
import org.neo4j.driver.Record;
import org.neo4j.driver.Value;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Path;
import org.neo4j.driver.types.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BasicQueryServiceImpl implements BasicQueryService {

    @Autowired
    Neo4jRepository neo4jRepository;

    @Override
    public GraphData queryOneEntityAndRelationships(String name) {
        List<Record> records = neo4jRepository.queryOneEntityAndRelations(name);
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
