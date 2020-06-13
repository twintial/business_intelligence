package com.bi.dbpedia.service;

import com.bi.dbpedia.dto.EntityParam;
import com.bi.dbpedia.model.elasticsearch.EsEntity;

import java.io.IOException;
import java.util.List;

public interface SearchService {

    List<EsEntity> searchEntity(EntityParam entityParam, int page, int size);
}
