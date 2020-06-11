package com.bi.dbpedia.controller;

import com.bi.dbpedia.dao.elasticsearch.UserRepository;
import com.bi.dbpedia.dao.neo4j.RelationshipRepository;
import com.bi.dbpedia.dao.neo4j.ResourceRepository;
import com.bi.dbpedia.dao.neo4j.Test;
import com.bi.dbpedia.model.elasticsearch.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.neo4j.driver.*;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Path;
import org.neo4j.driver.types.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Driver neo4jDriver;

    @GetMapping("/{testId}")
    public void test(@PathVariable String testId) throws NoSuchFieldException, IllegalAccessException {
        // mysql查询
        Map test = sqlSessionTemplate.selectOne("test", testId);
        System.out.println(test);
        // 驱动注入，neo4j查询
        Session neo4jSession = neo4jDriver.session();
        // 关系查询
        Result r = neo4jSession.run("match p=(n:Person{name:'1'})-[*1..3]->(m:Person{name:'3'}) return p");
        List<Record> list = r.list();
        for (Record record : list) {
            Value pv = record.get("p");
            Path path = pv.asPath();

            Node start = path.start(); // 开始节点
            System.out.println(start.asMap()); // 获得
            Node end = path.end();// 结束节点
            Iterable<Node> nodes = path.nodes();// 全部节点

            Iterable<Relationship> relationships = path.relationships(); // 全部路径
            for (Relationship relationship : relationships) {
                System.out.println(relationship.type()); // 路径的标签
            }
            System.out.println(pv);
        }

    }

    @GetMapping("/els")
    public void elsTest() {
        Iterable<User> all = userRepository.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }

}
