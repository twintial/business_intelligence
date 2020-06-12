package com.bi.dbpedia.dao.elasticsearch;

import com.bi.dbpedia.model.elasticsearch.EsPredicate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PredicateRepository extends ElasticsearchRepository<EsPredicate, String> {
}
