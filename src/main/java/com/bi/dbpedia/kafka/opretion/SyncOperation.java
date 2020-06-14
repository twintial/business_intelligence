package com.bi.dbpedia.kafka.opretion;

import com.bi.dbpedia.model.DataTable;

import java.util.List;
import java.util.Map;

public interface SyncOperation {
    void op(List<DataTable> newList, List<Map<String, String>> oldList);
}
