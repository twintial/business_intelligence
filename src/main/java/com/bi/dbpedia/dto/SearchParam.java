package com.bi.dbpedia.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SearchParam implements Serializable {

    private String name;
    private String label;
}
