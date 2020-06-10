package com.bi.dbpedia.dao.neo4j;

import com.bi.dbpedia.model.neo4j.ResourceRelationship;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface RelationshipRepository extends Neo4jRepository<ResourceRepository, Long> {

    @Query("match p=(n{uri:'http://dbpedia.org/resource/Abraham_Lincoln'})-[l]->(m) return p")
    List<ResourceRelationship> findResourceRelationship();
}
