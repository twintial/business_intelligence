package com.bi.dbpedia.controller;

import com.alibaba.fastjson.JSON;
import com.bi.dbpedia.dao.DataTableMapper;
import com.bi.dbpedia.dao.elasticsearch.EntityRepository;
import com.bi.dbpedia.dao.elasticsearch.PredicateRepository;
import com.bi.dbpedia.dao.elasticsearch.UserRepository;
import com.bi.dbpedia.model.Noun;
import com.bi.dbpedia.model.Predicate;
import com.bi.dbpedia.model.elasticsearch.EsEntity;
import com.bi.dbpedia.model.elasticsearch.EsPredicate;
import com.bi.dbpedia.model.elasticsearch.EsUser;
import com.bi.dbpedia.service.RedisService;
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

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityRepository entityRepository;

    @Autowired
    private PredicateRepository predicateRepository;

    @Autowired
    private DataTableMapper dataTableMapper;

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

    // es test
    @GetMapping("/els")
    public void elsTest() {
        Iterable<EsEntity> all = entityRepository.findAll();
        for (EsEntity esEntity : all) {
            System.out.println(esEntity);
        }
    }

    @Autowired
    private RedisService redisService;

    // redis test
    @GetMapping("/redis/{key}")
    public void redisTest(@PathVariable String key) {
        String s = redisService.get(key);
        System.out.println(s);
        EsUser esUser = JSON.parseObject(s, EsUser.class);
        System.out.println(esUser);
    }

    @GetMapping("redis/add/{key}")
    public void redisAdd(@PathVariable String key) {
        EsUser esUser = new EsUser("111", "111", "111", "111", "p");
        String s1 = JSON.toJSON(esUser).toString();
        System.out.println(s1);

        redisService.set(key, s1);
        redisService.expire(key, 1);
    }

    @GetMapping("/els/saveall")
    public void saveAll() {
        List<Noun> nouns = dataTableMapper.selectNoun();
        Set<EsEntity> entities = new HashSet<>();
        Set<String> labels1 = new HashSet<>();
        for (Noun data : nouns) {
            entities.add(new EsEntity(data.getObject(), data.getObjectUri(), data.getObjectLabel()));
            entities.add(new EsEntity(data.getSubject(), data.getSubjectUri(), data.getSubjectLabel()));

            labels1.add(data.getSubjectLabel());
            labels1.add(data.getObjectLabel());
        }
        entityRepository.saveAll(entities);
        dataTableMapper.insertResourceLabel(labels1);


        List<Predicate> predicates = dataTableMapper.selectPred();
        Set<String> labels2 = new HashSet<>();
        Set<EsPredicate> predicateSet = new HashSet<>();
        for (Predicate predicate : predicates) {
            predicateSet.add(new EsPredicate(predicate.getPredicate(), predicate.getPredicateUri(), predicate.getPredicateLabel()));
            labels2.add(predicate.getPredicateLabel());
        }
        predicateRepository.saveAll(predicateSet);
        dataTableMapper.insertPredicateLabel(labels2);
    }

}
