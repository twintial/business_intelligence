package com.bi.dbpedia.kafka.dbsync;

import com.bi.dbpedia.dao.elasticsearch.EntityRepository;
import com.bi.dbpedia.dao.elasticsearch.PredicateRepository;
import com.bi.dbpedia.model.DataTable;
import com.bi.dbpedia.model.Predicate;
import com.bi.dbpedia.model.elasticsearch.EsEntity;
import com.bi.dbpedia.model.elasticsearch.EsPredicate;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
@Qualifier("es")
public class EsSync implements AbstractSync {

    @Autowired
    EntityRepository entityRepository;
    @Autowired
    PredicateRepository predicateRepository;

    @Autowired
    @Qualifier("esClient")
    private RestHighLevelClient highLevelClient;

    @Override
    public void update(List<DataTable> newData, List<Map<String, String>> oldList) {
        System.out.println("es update");

        for (int i = 0; i < newData.size(); i++) {
            DataTable data = newData.get(i);
            Map<String, String> oldMap = oldList.get(i);
            // 更新object
            String objectId = oldMap.get("object") != null ? oldMap.get("object"): data.getObject();
            entityRepository.delete(new EsEntity(objectId));
            entityRepository.save(new EsEntity(data.getObject(), data.getObjectUri(), data.getObjectLabel()));

            // 更新subject
            String subjectId = oldMap.get("subject") != null ? oldMap.get("subject"): data.getSubject();
            entityRepository.delete(new EsEntity(subjectId));
            entityRepository.save(new EsEntity(data.getSubject(), data.getSubjectUri(), data.getSubjectLabel()));

            // 更新predicate
            String predicateId = oldMap.get("predicate") != null ? oldMap.get("predicate"): data.getPredicate();
            predicateRepository.delete(new EsPredicate(predicateId));
            predicateRepository.save(new EsPredicate(data.getPredicate(), data.getPredicateUri(), data.getPredicateLabel()));
        }
//
//        UpdateRequest updateRequest = new UpdateRequest("entity", "a");
//        IndexRequest indexRequest = new IndexRequest("entity");
//        Map<String, String> source = new HashMap<>();
//        source.put("name", "new");
//        indexRequest.source(source);
//        updateRequest.doc(indexRequest);
//        try {
//            highLevelClient.update(updateRequest, RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void delete(List<DataTable> deleteList) {
        System.out.println("es delete");
//        for (DataTable data : deleteList) {
//            entityRepository.delete(new EsEntity(data.getObject(), data.getObjectUri(), data.getObjectLabel()));
//            entityRepository.delete(new EsEntity(data.getSubject(), data.getSubjectUri(), data.getSubjectLabel()));
//            predicateRepository.delete(new EsPredicate(data.getPredicate(), data.getPredicateUri(), data.getPredicateLabel()));
//        }
    }

    @Override
    public void insert(List<DataTable> insertList) {
        System.out.println("es insert");
        for (DataTable data : insertList) {
            entityRepository.save(new EsEntity(data.getObject(), data.getObjectUri(), data.getObjectLabel()));
            entityRepository.save(new EsEntity(data.getSubject(), data.getSubjectUri(), data.getSubjectLabel()));
            predicateRepository.save(new EsPredicate(data.getPredicate(), data.getPredicateUri(), data.getPredicateLabel()));
        }
    }
}
