package com.bi.dbpedia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SearchResult implements Serializable {

    private String name;
    private String label;
    private String uri;
}
