package com.bi.dbpedia.model.neo4j;

import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Property;

@NodeEntity(label = "Resource")
@Data
public class ResourceNode {
    @Id
    private long nodeId;

    @Property(name = "uri")
    private String uri;
}
