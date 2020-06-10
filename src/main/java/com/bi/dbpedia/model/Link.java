package com.bi.dbpedia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link implements Serializable {

    // 对应Node的id
    private String source;
    // 对应Node的id
    private String target;
    private String type;
}
