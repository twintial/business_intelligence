package com.bi.dbpedia.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EvaluationData {

    private List<String> products;
    private List<String> service;
    private double score;
}
