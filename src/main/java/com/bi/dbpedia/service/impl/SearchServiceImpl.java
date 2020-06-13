package com.bi.dbpedia.service.impl;

import com.bi.dbpedia.dto.SearchParam;
import com.bi.dbpedia.dto.SearchResult;
import com.bi.dbpedia.service.SearchService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    @Qualifier("esClient")
    private RestHighLevelClient highLevelClient;

    @Override
    public List<SearchResult> searchResource(SearchParam searchParam, int page, int size) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if (!StringUtils.isEmpty(searchParam.getName())) {
            // boolQueryBuilder.must(QueryBuilders.wildcardQuery("name", "*" + entityParam.getName() + "*"));
            // 暂时先这样
            boolQueryBuilder.must(QueryBuilders.prefixQuery("name", searchParam.getName().toLowerCase()));
        }
        if (!StringUtils.isEmpty(searchParam.getLabel())) {
            boolQueryBuilder.must(QueryBuilders.prefixQuery("label", searchParam.getLabel().toLowerCase()));
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
        List<SearchResult> searchResults = new ArrayList<>();
        for (SearchHit hit : searchHits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            searchResults.add(new SearchResult(sourceAsMap.get("name").toString(),
                    sourceAsMap.get("label").toString(), sourceAsMap.get("uri").toString()));
        }
        return searchResults;
    }

    @Override
    public List<SearchResult> searchPredicate(SearchParam searchParam, int page, int size) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if (!StringUtils.isEmpty(searchParam.getName())) {
            // boolQueryBuilder.must(QueryBuilders.wildcardQuery("name", "*" + entityParam.getName() + "*"));
            // 暂时先这样
            boolQueryBuilder.must(QueryBuilders.prefixQuery("name", searchParam.getName().toLowerCase()));
        }
        if (!StringUtils.isEmpty(searchParam.getLabel())) {
            boolQueryBuilder.must(QueryBuilders.prefixQuery("label", searchParam.getLabel().toLowerCase()));
        }

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolQueryBuilder);
        sourceBuilder.from((page - 1) * size);
        sourceBuilder.size(size);


        SearchRequest searchRequest = new SearchRequest("predicate");
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
        List<SearchResult> searchResults = new ArrayList<>();
        for (SearchHit hit : searchHits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            searchResults.add(new SearchResult(sourceAsMap.get("name").toString(),
                    sourceAsMap.get("label").toString(), sourceAsMap.get("uri").toString()));
        }
        return searchResults;
    }
}
