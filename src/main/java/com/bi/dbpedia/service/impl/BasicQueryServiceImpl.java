package com.bi.dbpedia.service.impl;

import com.alibaba.fastjson.JSON;
import com.bi.dbpedia.dao.Neo4jRepository;
import com.bi.dbpedia.dto.Neo4jQueryParam;
import com.bi.dbpedia.dto.OneNodeParam;
import com.bi.dbpedia.dto.TwoNodeParam;
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
    public GraphData queryOneEntityAndRelationships(OneNodeParam param) {
        // 添加了缓存
        String queryKey = "One_" + param.toString();
        String result = redisService.get(queryKey);
        if (result == null) {
            List<Record> records = neo4jRepository.queryOneEntityAndRelations(param);
            if (records == null) {
                return null;
            }
            GraphData graphData = DataFormat.CovertRecordToData(records);
            redisService.set(queryKey, JSON.toJSONString(graphData));
            redisService.expire(queryKey, 1);
            return graphData;
        } else {
            return JSON.parseObject(result, GraphData.class);
        }
    }

    @Override
    public GraphData queryTwoEntityWithNLinks(TwoNodeParam param) {
        // 添加了缓存
        String queryKey = "Two_" + param.toString();
        String result = redisService.get(queryKey);
        if (result == null) {
            List<Record> records = neo4jRepository.queryTwoEntityWithNLink(param);
            if (records == null) {
                return null;
            }
            GraphData graphData = DataFormat.CovertRecordToData(records);
            redisService.set(queryKey, JSON.toJSONString(graphData));
            redisService.expire(queryKey, 1);
            return graphData;
        } else {
            return JSON.parseObject(result, GraphData.class);
        }
    }
}
