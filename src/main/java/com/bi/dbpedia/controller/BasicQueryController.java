package com.bi.dbpedia.controller;


import com.bi.dbpedia.common.api.CommonResult;
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
    public CommonResult<GraphData> queryOneEntityAndRelationShips(@RequestParam("name") String name) {
        GraphData graphData = basicQueryService.queryOneEntityAndRelationships(name);
        return CommonResult.success(graphData, "query one success");
    }

    @PostMapping("/two")
    public CommonResult<GraphData> queryTwoEntityWithNLinks(
            @RequestParam("name1") String name1, @RequestParam("name2") String name2, @RequestParam("nLinks") int n) {
        GraphData graphData = basicQueryService.queryTwoEntityWithNLinks(name1, name2, n);
        return CommonResult.success(graphData, "query two success");
    }
}
