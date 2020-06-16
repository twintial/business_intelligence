package com.bi.dbpedia.dao.elasticsearch;

import com.bi.dbpedia.model.elasticsearch.EsUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<EsUser, String>{
}
