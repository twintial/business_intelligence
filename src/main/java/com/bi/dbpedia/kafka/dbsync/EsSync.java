package com.bi.dbpedia.kafka.dbsync;

import com.bi.dbpedia.dao.elasticsearch.EntityRepository;
import com.bi.dbpedia.dao.elasticsearch.PredicateRepository;
import com.bi.dbpedia.model.DataTable;
import com.bi.dbpedia.model.elasticsearch.EsEntity;
import com.bi.dbpedia.model.elasticsearch.EsPredicate;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
    public void update(List<DataTable> newList, List<Map<String, String>> oldList) {
        System.out.println("es update");
        highLevelClient.update()
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
