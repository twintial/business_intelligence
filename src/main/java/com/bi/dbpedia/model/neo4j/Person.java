package com.bi.dbpedia.model.neo4j;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.util.List;

@NodeEntity(label = "Person")
@Data
public class Person {

    @Id
    private long nodeId;

    @Property(name = "name")
    private String name;

    @Property(name = "type")
    private List<String> type;
}
