package com.bi.dbpedia.service.impl;

import com.alibaba.fastjson.JSON;
import com.bi.dbpedia.dao.Neo4jRepository;
import com.bi.dbpedia.dto.Neo4jQueryParam;
import com.bi.dbpedia.model.GraphData;
import com.bi.dbpedia.service.BasicQueryService;
import com.bi.dbpedia.service.RedisService;
import com.bi.dbpedia.util.DataFormat;
import org.neo4j.driver.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BasicQueryServiceImpl implements BasicQueryService {

    @Autowired
    Neo4jRepository neo4jRepository;

    @Autowired
    RedisService redisService;

    @Override
    public GraphData queryOneEntityAndRelationships(String name) {
        // 添加了缓存
        String queryType = "One_";
        String result = redisService.get(queryType + name);
        if (result == null) {
            List<Record> records = neo4jRepository.queryOneEntityAndRelations(name);
            GraphData graphData = DataFormat.CovertRecordToData(records);
            redisService.set(queryType + name, JSON.toJSONString(graphData));
            redisService.expire(queryType + name, 1);
            return graphData;
        } else {
            return JSON.parseObject(result, GraphData.class);
        }
    }

    @Override
    public GraphData queryTwoEntityWithNLinks(String name1, String name2, int nLinks) {
        // 之后需要添加缓存
        List<Record> records = neo4jRepository.queryTwoEntityWithNLink(name1, name2, nLinks);
        return DataFormat.CovertRecordToData(records);
    }

    @Override
    public GraphData basicQuery(Neo4jQueryParam param) {
        return null;
    }
}
