package com.bi.dbpedia.dao;

import com.bi.dbpedia.dto.OneNodeParam;
import com.bi.dbpedia.dto.TwoNodeParam;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class Neo4jRepository {

    private Session neo4jTemplate;

    @Autowired
    public Neo4jRepository(Driver neo4jDriver) {
        neo4jTemplate = neo4jDriver.session();
    }

    public List<Record> queryWithCyber(String cyber, Map<String, Object> params) {
        Result result;
        if (params != null) {
            result = neo4jTemplate.run(cyber, params);
        } else {
            result = neo4jTemplate.run(cyber);
        }
        return result.list();
    }

    public List<Record> queryOneEntityAndRelations(OneNodeParam param) {
        String cyber = "match p=(n:Resource)-[l:Relation]%s(m:Resource) %s return p";
        String direction = param.getIsUnidirectional() ? "->" : "-";
        StringBuilder constrains = new StringBuilder("where");
        if (!StringUtils.isEmpty(param.getNodeName())) {
            constrains.append("and n.name=~'(?i)").append(param.getNodeName()).append("'");
        }
        if (!StringUtils.isEmpty(param.getNodeLabel())) {
            constrains.append("and n.label=~'(?i)").append(param.getNodeLabel()).append("'");
        }
        if (!StringUtils.isEmpty(param.getLinkName())) {
            constrains.append("and l.name=~'(?i)").append(param.getLinkName()).append("'");
        }
        if (!StringUtils.isEmpty(param.getLinkLabel())) {
            constrains.append("and l.label=~'(?i)").append(param.getLinkLabel()).append("'");
        }
        if (constrains.toString().equals("where")) {
            return null;
        }
        cyber = String.format(cyber, direction, constrains.toString().replaceFirst("and", ""));
        log.info(cyber);
        return queryWithCyber(cyber, null);
        // 无视大小写
//        name = "(?i)" + name;
//        // 可以考虑将cyber语句放入一个文件内或者数据库内
//        String cyber = "match p=(n:Resource)-[]-(m:Resource) where n.name=~$name return p";
//        Map<String, Object> params = new HashMap<>();
//        params.put("name", name);
    }

    public List<Record> queryTwoEntityWithNLink(TwoNodeParam param) {
        String cyber = "match p=(n:Resource)-[l:Relation*1..%d%s]%s(m:Resource) %s return p";

        StringBuilder lc = new StringBuilder();
        if (!StringUtils.isEmpty(param.getLinkName())) {
            lc.append(String.format("{name:'%s'", param.getLinkName()));
        }
        if (!StringUtils.isEmpty(param.getLinkLabel())) {
            lc.append(String.format(",label:'%s'", param.getLinkLabel()));
        }
        if (!lc.toString().equals("")) {
            lc.append("}");
        }

        String direction = param.getIsUnidirectional() ? "->" : "-";
        StringBuilder constrains = new StringBuilder("where");
        if (!StringUtils.isEmpty(param.getNodeName1())) {
            constrains.append("and n.name=~'(?i)").append(param.getNodeName1()).append("'");
        }
        if (!StringUtils.isEmpty(param.getNodeLabel1())) {
            constrains.append("and n.label=~'(?i)").append(param.getNodeLabel1()).append("'");
        }
        if (!StringUtils.isEmpty(param.getNodeName2())) {
            constrains.append("and m.name=~'(?i)").append(param.getNodeName2()).append("'");
        }
        if (!StringUtils.isEmpty(param.getNodeLabel2())) {
            constrains.append("and m.label=~'(?i)").append(param.getNodeLabel2()).append("'");
        }

        if (constrains.toString().equals("where")) {
            return null;
        }
        cyber = String.format(cyber, param.getMaxLinks(), lc.toString(),
                direction, constrains.toString().replaceFirst("and", ""));
        log.info(cyber);
        System.out.println(cyber);
//        name1 = "(?i)" + name1;
//        name2 = "(?i)" + name2;
//        // 可以考虑将cyber语句放入一个文件内或者数据库内
//        String cyber = String.format("match p=(n:Resource)-[*1..%d]-(m:Resource) where n.name=~$name1 and m.name=~$name2 return p", n);
//        Map<String, Object> params = new HashMap<>();
//        params.put("name1", name1);
//        params.put("name2", name2);
        return queryWithCyber(cyber, null);
    }

    public List<Record> searchFoundationPlace() {
        String cyber = "match (n:Resource)-[a]->(m:Resource) where m.name=~'(?i)internet' and a.name=~'(?)industry' " +
                "match (n)-[l:Relation]->(x:Resource) where l.name=~'(?i)foundationPlace' " +
                "return x.name as name, count(x) as value order by value desc";
        return queryWithCyber(cyber, null);
    }

    public List<Record> searchProducts() {
        String cyber = "match (n:Resource)-[a]->(m:Resource) where m.name=~'(?i)internet' and a.name=~'(?)industry' " +
                "match (n)-[l:Relation]->(x:Resource) where l.name=~'(?i)product' " +
                "return x.name as name, count(x) as value order by value desc limit 10 ";
        return queryWithCyber(cyber, null);
    }

    public List<Record> searchCompany() {
        String cyber = "match (n:Resource)-[a]->(m:Resource) where m.name=~'(?i)internet' and a.name=~'(?)industry' " +
                "match (n)-[l:Relation]->(x:Resource) where l.name=~'(?i)service' " +
                "match (n)-[o:Relation]->(y:Resource) where o.name=~'(?i)product' " +
                "return distinct(n.name) as name";
        return queryWithCyber(cyber, null);
    }

    public List<Record> searchServiceByName(String companyName) {
        String cyber = "match (n:Resource{name:$name})-[l:Relation{name:'service'}]->(m:Resource) return m.name as name";
        Map<String, Object> params = new HashMap<>();
        params.put("name", companyName);
        return queryWithCyber(cyber, params);
    }

    public List<Record> searchProductsByName(String companyName) {
        String cyber = "match (n:Resource{name:$name})-[l:Relation{name:'product'}]->(m:Resource) return m.name as name";
        Map<String, Object> params = new HashMap<>();
        params.put("name", companyName);
        return queryWithCyber(cyber, params);
    }
}
