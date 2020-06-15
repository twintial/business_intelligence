package com.bi.dbpedia.service;

import com.bi.dbpedia.dto.Neo4jQueryParam;
import com.bi.dbpedia.dto.OneNodeParam;
import com.bi.dbpedia.dto.TwoNodeParam;
import com.bi.dbpedia.model.GraphData;

public interface BasicQueryService {

    GraphData queryOneEntityAndRelationships(OneNodeParam param);

    GraphData queryTwoEntityWithNLinks(TwoNodeParam param);
}
