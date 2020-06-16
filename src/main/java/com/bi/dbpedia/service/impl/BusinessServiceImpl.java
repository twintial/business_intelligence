package com.bi.dbpedia.service.impl;

import com.bi.dbpedia.config.Neo4jProperties;
import com.bi.dbpedia.dao.Neo4jRepository;
import com.bi.dbpedia.model.Business;
import com.bi.dbpedia.service.BusinessService;
import com.bi.dbpedia.util.ConvertToCountry;
import org.neo4j.driver.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    Neo4jRepository neo4jRepository;

    @Override
    public List<Business> searchFoundationPlace() {
        List<Record> records = neo4jRepository.searchFoundationPlace();
        List<Business> businessList = new ArrayList<>();
        for (Record record : records) {
            businessList.add(new Business(
                    ConvertToCountry.convert(String.valueOf(record.get("name")).replace("\"", "")),
                    Long.parseLong(String.valueOf(record.get("value")))));
        }
        return businessList;
    }

    @Override
    public List<Business> searchProducts() {
        List<Record> records = neo4jRepository.searchProducts();
        List<Business> businessList = new ArrayList<>();
        for (Record record : records) {
            businessList.add(new Business(
                    ConvertToCountry.convert(String.valueOf(record.get("name")).replace("\"", "")),
                    Long.parseLong(String.valueOf(record.get("value")))));
        }
        return businessList;
    }
}
