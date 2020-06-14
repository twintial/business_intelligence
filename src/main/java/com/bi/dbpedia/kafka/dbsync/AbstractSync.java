package com.bi.dbpedia.kafka.dbsync;

import com.bi.dbpedia.model.DataTable;

import java.util.List;
import java.util.Map;

public interface AbstractSync {
    void update(List<DataTable> newList, List<Map<String, String>> oldList);
    void delete(List<DataTable> deleteList);
    void insert(List<DataTable> insertList);
}
