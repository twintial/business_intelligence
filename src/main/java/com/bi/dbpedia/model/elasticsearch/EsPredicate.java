package com.bi.dbpedia.model.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@Document(indexName = "predicate", refreshInterval = "30s")
public class EsPredicate {

    @Id
    private String name;
    private String uri;
    @Field(type = FieldType.Text, analyzer = "ik_smart", index = true, store = false, searchAnalyzer = "ik_smart")
    private String label;

    public EsPredicate(String name) {
        this.name = name;
    }
}
