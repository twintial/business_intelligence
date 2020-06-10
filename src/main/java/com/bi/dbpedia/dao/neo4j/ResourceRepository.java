package com.bi.dbpedia.dao.neo4j;

import com.bi.dbpedia.model.neo4j.ResourceNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.GraphRepositoryQuery;
import org.springframework.stereotype.Component;

public interface ResourceRepository extends Neo4jRepository<ResourceNode, Long> {

    @Query("match(n{uri:'http://dbpedia.org/resource/Abraham_Lincoln'}) return n")
    ResourceNode getOne();
}
