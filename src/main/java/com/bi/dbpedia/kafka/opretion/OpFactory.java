package com.bi.dbpedia.kafka.opretion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OpFactory {

    private Map<String, SyncOperation> opMap = new HashMap<>();

    public OpFactory(@Qualifier("insert") SyncOperation insert,
                     @Qualifier("delete") SyncOperation delete,
                     @Qualifier("update") SyncOperation update) {

        opMap.put("DELETE", delete);
        opMap.put("INSERT", insert);
        opMap.put("UPDATE", update);
    }

    public SyncOperation getOp(String type){
        return opMap.get(type);
    }
}
