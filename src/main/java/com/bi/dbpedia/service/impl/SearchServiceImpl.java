package com.bi.dbpedia.service.impl;

import com.bi.dbpedia.dao.elasticsearch.EntityRepository;
import com.bi.dbpedia.dto.EntityParam;
import com.bi.dbpedia.model.elasticsearch.EsEntity;
import com.bi.dbpedia.service.SearchService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    @Qualifier("esClient")
    private RestHighLevelClient highLevelClient;

    @Override
    public List<EsEntity> searchEntity(EntityParam entityParam, int page, int size) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if (!StringUtils.isEmpty(entityParam.getName())) {
            // boolQueryBuilder.must(QueryBuilders.wildcardQuery("name", "*" + entityParam.getName() + "*"));
            // 暂时先这样
            boolQueryBuilder.must(QueryBuilders.fuzzyQuery("name", entityParam.getName()));
        }
        if (!StringUtils.isEmpty(entityParam.getLabel())) {
            boolQueryBuilder.must(QueryBuilders.queryStringQuery(entityParam.getLabel()).field("label"));
        }

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolQueryBuilder);
        sourceBuilder.from((page - 1) * size);
        sourceBuilder.size(size);


        SearchRequest searchRequest = new SearchRequest("entity");
        searchRequest.source(sourceBuilder);


//        source.query(boolQueryBuilder);
//        source.from((page - 1) * size);
//        source.size(size);
        SearchResponse response = null;
        try {
            response = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SearchHits hits = response.getHits();
        SearchHit[] searchHits = hits.getHits();
        System.out.println(searchHits.length);
        for (SearchHit hit : searchHits) {
            System.out.println(hit.getSourceAsString());
        }
        return null;
    }
}
