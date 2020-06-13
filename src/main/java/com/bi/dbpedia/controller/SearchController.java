package com.bi.dbpedia.controller;


import com.bi.dbpedia.common.api.CommonResult;
import com.bi.dbpedia.dto.EsPageInfo;
import com.bi.dbpedia.dto.SearchParam;
import com.bi.dbpedia.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("/resource/{page}/{size}")
    public CommonResult<EsPageInfo> searchResource(@RequestBody SearchParam searchParam, @PathVariable int page, @PathVariable int size) {
        EsPageInfo results = searchService.searchResource(searchParam, page, size);
        return CommonResult.success(results, "search resource success");
    }

    @PostMapping("/predicate/{page}/{size}")
    public CommonResult<EsPageInfo> searchPredicate(@RequestBody SearchParam searchParam, @PathVariable int page, @PathVariable int size) {
        EsPageInfo results = searchService.searchPredicate(searchParam, page, size);
        return CommonResult.success(results, "search predicate success");
    }
}
