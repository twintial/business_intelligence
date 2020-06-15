package com.bi.dbpedia.controller;


import com.bi.dbpedia.common.api.CommonResult;
import com.bi.dbpedia.dto.Neo4jQueryParam;
import com.bi.dbpedia.dto.OneNodeParam;
import com.bi.dbpedia.dto.TwoNodeParam;
import com.bi.dbpedia.model.GraphData;
import com.bi.dbpedia.service.BasicQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basic")
public class BasicQueryController {

    @Autowired
    BasicQueryService basicQueryService;

    @PostMapping("/one")
    public CommonResult<GraphData> queryOneEntityAndRelationShips(@RequestBody OneNodeParam oneNodeParam) {
        GraphData graphData = basicQueryService.queryOneEntityAndRelationships(oneNodeParam);
        return CommonResult.success(graphData, "query one success");
    }

    @PostMapping("/two")
    public CommonResult<GraphData> queryTwoEntityWithNLinks(@RequestBody TwoNodeParam twoNodeParam) {
        GraphData graphData = basicQueryService.queryTwoEntityWithNLinks(twoNodeParam);
        return CommonResult.success(graphData, "query two success");
    }

}
