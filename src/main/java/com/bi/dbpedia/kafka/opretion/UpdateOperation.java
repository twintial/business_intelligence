package com.bi.dbpedia.kafka.opretion;

import com.bi.dbpedia.kafka.dbsync.AbstractSync;
import com.bi.dbpedia.model.DataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Qualifier("update")
public class UpdateOperation implements SyncOperation {

    @Autowired
    @Qualifier("es")
    AbstractSync esSync;

    @Autowired
    @Qualifier("neo4j")
    AbstractSync neo4jSync;

    @Override
    public void op(List<DataTable> newList, List<Map<String, String>> oldList) {
        esSync.update(newList, oldList);
        neo4jSync.update(newList, oldList);
    }
}
