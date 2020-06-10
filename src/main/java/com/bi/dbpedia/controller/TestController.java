package com.bi.dbpedia.controller;

import com.bi.dbpedia.dao.ResourceRepository;
import com.bi.dbpedia.model.neo4j.ResourceNode;
import org.mybatis.spring.SqlSessionTemplate;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.ogm.session.Neo4jSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private Driver neo4jDriver;

    @Autowired
    private ResourceRepository resourceRepository;

    @GetMapping("/{testId}")
    public void test(@PathVariable String testId){
        // mysql查询
        Map test = sqlSessionTemplate.selectOne("test", testId);
        System.out.println(test);
        // 驱动注入，neo4j查询
        Session neo4jSession = neo4jDriver.session();
        Result r = neo4jSession.run("match(n{uri:'http://dbpedia.org/resource/Abraham_Lincoln'}) return n");
        while (r.hasNext()) {
            Record next = r.next();
            System.out.println(next);
        }
        // springboot集成，neo4j查询
        ResourceNode node = resourceRepository.getOne();
        System.out.println(node);
    }
}
