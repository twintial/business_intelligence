package com.bi.dbpedia.service;

import com.bi.dbpedia.dto.SearchParam;
import com.bi.dbpedia.dto.SearchResult;

import java.util.List;

public interface SearchService {

    List<SearchResult> searchResource(SearchParam searchParam, int page, int size);

    List<SearchResult> searchPredicate(SearchParam searchParam, int page, int size);
}
