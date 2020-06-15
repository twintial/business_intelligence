package com.bi.dbpedia.controller;


import com.bi.dbpedia.common.api.CommonResult;
import com.bi.dbpedia.dao.LabelMapper;
import com.bi.dbpedia.dto.EsPageInfo;
import com.bi.dbpedia.dto.SearchParam;
import com.bi.dbpedia.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;
    @Autowired
    private LabelMapper labelMapper;

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

    @GetMapping("/label/resource")
    public CommonResult<List<String>> searchResourceLabel() {
        List<String> labels = labelMapper.selectResourceLabels();
        return CommonResult.success(labels, "search resource labels success");
    }

    @GetMapping("/label/predicate")
    public CommonResult<List<String>> searchPredicateLabel() {
        List<String> labels = labelMapper.selectPredicateLabels();
        return CommonResult.success(labels, "search predicate labels success");
    }
}
