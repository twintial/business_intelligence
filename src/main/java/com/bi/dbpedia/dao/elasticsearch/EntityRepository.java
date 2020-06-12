package com.bi.dbpedia.dao.elasticsearch;

import com.bi.dbpedia.model.elasticsearch.EsEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EntityRepository extends ElasticsearchRepository<EsEntity, String> {
}
