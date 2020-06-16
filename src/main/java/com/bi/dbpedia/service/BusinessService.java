package com.bi.dbpedia.service;

import com.bi.dbpedia.model.Business;

import java.util.List;

public interface BusinessService {

    List<Business> searchFoundationPlace();

    List<Business> searchProducts();
}
