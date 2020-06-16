package com.bi.dbpedia.util;

import java.util.List;

/**
 * 评价公司分数
 */
public class EvaluateCompany {

    public static double evaluate(List<String> products, List<String> service) {
        return 5 * products.size() + 10 * service.size();
    }
}
