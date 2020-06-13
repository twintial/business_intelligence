package com.bi.dbpedia.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Neo4jQueryParam implements Serializable {

    private String resource1;
    private String resLabel1;

    private String predicate;
    private String preLabel;

    private String resource2;
    private String resLabel2;

    private int linkMin;
    private int linkMax;
}
