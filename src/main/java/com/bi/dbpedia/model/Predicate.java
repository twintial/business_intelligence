package com.bi.dbpedia.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Predicate implements Serializable {

    private String predicate;
    private String predicateUri;
    private String predicateLabel;
}
