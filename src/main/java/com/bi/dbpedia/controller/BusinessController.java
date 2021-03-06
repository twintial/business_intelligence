package com.bi.dbpedia.controller;

import com.bi.dbpedia.common.api.CommonResult;
import com.bi.dbpedia.model.Business;
import com.bi.dbpedia.model.EvaluationData;
import com.bi.dbpedia.service.BusinessService;
import com.bi.dbpedia.util.EvaluateCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 搜索互联网公司（有产品和服务）
     * @return
     */
    @GetMapping("/company")
    public CommonResult<List<String>> getCompany() {
        List<String> list = businessService.searchCompany();
        return CommonResult.success(list, "search company success");
    }

    @GetMapping("/info")
    public CommonResult<EvaluationData> getInfoByName(@RequestParam String companyName) {
        List<String> products = businessService.searchProductsByName(companyName);
        List<String> service = businessService.searchServiceByName(companyName);
        EvaluationData evaluationData = new EvaluationData(products, service, EvaluateCompany.evaluate(products, service));
        return CommonResult.success(evaluationData, "get company info and score success");
    }
}
