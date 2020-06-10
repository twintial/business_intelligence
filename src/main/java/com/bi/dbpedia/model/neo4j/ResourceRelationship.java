package com.bi.dbpedia.model.neo4j;

import lombok.Data;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "battle")
@Data
public class ResourceRelationship {

    @Id
    private Long id;

    @StartNode
    private ResourceNode r1;
    @EndNode
    private ResourceNode r2;
}
