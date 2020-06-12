package com.bi.dbpedia.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;


@Data
@AllArgsConstructor
public class GraphData implements Serializable {

    private Set<Resource> nodes;
    private Set<Link> links;
}
