package com.bi.dbpedia.dao.elasticsearch;

import com.bi.dbpedia.model.elasticsearch.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<User, String>{
}
