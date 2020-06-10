package com.bi.dbpedia.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
public class GraphData implements Serializable {

    private Set<Entity> nodes;
    private Set<Link> links;
}
