package com.bi.dbpedia.dao.neo4j;

import com.bi.dbpedia.model.neo4j.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface Test extends Neo4jRepository<Person, Long> {

    @Query("match (n:Person) return n")
    Person getOne();
}
