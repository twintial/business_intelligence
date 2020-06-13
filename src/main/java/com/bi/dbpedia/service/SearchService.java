package com.bi.dbpedia.service;

import com.bi.dbpedia.dto.EsPageInfo;
import com.bi.dbpedia.dto.SearchParam;

public interface SearchService {

    EsPageInfo searchResource(SearchParam searchParam, int page, int size);

    EsPageInfo searchPredicate(SearchParam searchParam, int page, int size);
}
