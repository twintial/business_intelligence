package com.bi.dbpedia.service.impl;

import com.bi.dbpedia.dao.Neo4jRepository;
import com.bi.dbpedia.model.Entity;
import com.bi.dbpedia.model.GraphData;
import com.bi.dbpedia.model.Link;
import com.bi.dbpedia.service.BasicQueryService;
import com.bi.dbpedia.util.DataFormat;
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
        // 之后需要添加缓存
        List<Record> records = neo4jRepository.queryOneEntityAndRelations(name);
        return DataFormat.CovertRecordToData(records);
    }

    @Override
    public GraphData queryTwoEntityWithNLinks(String name1, String name2, int nLinks) {
        // 之后需要添加缓存
        List<Record> records = neo4jRepository.queryTwoEntityWithNLink(name1, name2, nLinks);
        return DataFormat.CovertRecordToData(records);
    }
}
