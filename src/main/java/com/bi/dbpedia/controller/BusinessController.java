package com.bi.dbpedia.controller;

import com.bi.dbpedia.common.api.CommonResult;
import com.bi.dbpedia.model.Business;
import com.bi.dbpedia.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @GetMapping("/foundation")
    public CommonResult<List<Business>> searchFoundation() {
        List<Business> list = businessService.searchFoundationPlace();
        return CommonResult.success(list, "search foundation success");
    }

    @GetMapping("/products")
    public CommonResult<List<Business>> searchProducts() {
        List<Business> list = businessService.searchProducts();
        return CommonResult.success(list, "search products success");
    }
}
