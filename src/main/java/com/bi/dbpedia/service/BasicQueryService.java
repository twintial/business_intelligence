package com.bi.dbpedia.service;

import com.bi.dbpedia.dto.Neo4jQueryParam;
import com.bi.dbpedia.model.GraphData;

public interface BasicQueryService {

    GraphData queryOneEntityAndRelationships(String name);

    GraphData queryTwoEntityWithNLinks(String name1, String name2, int nLinks);

    GraphData basicQuery(Neo4jQueryParam param);
}
