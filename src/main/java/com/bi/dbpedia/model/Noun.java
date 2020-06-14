package com.bi.dbpedia.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Noun implements Serializable {

    private String subject;
    private String object;
    private String subjectUri;
    private String objectUri;
    private String subjectLabel;
    private String objectLabel;
}
