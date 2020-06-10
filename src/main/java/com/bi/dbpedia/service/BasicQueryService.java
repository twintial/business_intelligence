package com.bi.dbpedia.service;

import com.bi.dbpedia.model.GraphData;

public interface BasicQueryService {

    GraphData queryOneEntityAndRelationships(String name);
}
