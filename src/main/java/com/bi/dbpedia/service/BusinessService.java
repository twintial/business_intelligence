package com.bi.dbpedia.service;

import com.bi.dbpedia.model.Business;

import java.util.List;

public interface BusinessService {

    List<Business> searchFoundationPlace();

    List<Business> searchProducts();

    List<String> searchCompany();

    List<String> searchServiceByName(String name);

    List<String> searchProductsByName(String name);
}
