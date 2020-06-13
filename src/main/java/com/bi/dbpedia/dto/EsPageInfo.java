package com.bi.dbpedia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class EsPageInfo implements Serializable {

    private List<SearchResult> results;
    private long total;
    private long totalPages;
}
