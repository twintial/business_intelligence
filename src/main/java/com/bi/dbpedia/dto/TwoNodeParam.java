package com.bi.dbpedia.dto;

import lombok.Data;

@Data
public class TwoNodeParam {

    private String nodeName1;
    private String nodeLabel1;

    private String linkName;
    private String linkLabel;

    private String nodeName2;
    private String nodeLabel2;

    private Boolean isUnidirectional;
    private Integer maxLinks;
}
