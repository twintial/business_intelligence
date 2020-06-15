package com.bi.dbpedia.dto;

import lombok.Data;

@Data
public class OneNodeParam {

    private String nodeName;
    private String nodeLabel;

    private String linkName;
    private String linkLabel;

    private Boolean isUnidirectional;
}
