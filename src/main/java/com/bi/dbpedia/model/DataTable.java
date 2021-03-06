package com.bi.dbpedia.model;

import lombok.Data;

@Data
public class DataTable {

    private String subject;
    private String predicate;
    private String object;
    private String subjectUri;
    private String predicateUri;
    private String objectUri;
    private String subjectLabel;
    private String predicateLabel;
    private String objectLabel;

}
