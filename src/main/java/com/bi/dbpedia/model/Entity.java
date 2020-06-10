package com.bi.dbpedia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entity implements Serializable {

    // 标示符号
    private Long id;
    private String name;
    private String uri;
    private List<String> label;
}
