package com.bi.dbpedia.kafka.dbsync;

import com.bi.dbpedia.model.DataTable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Qualifier("es")
public class EsSync implements AbstractSync {

    @Override
    public void update(List<DataTable> newList, List<Map<String, String>> oldList) {
        System.out.println("es update");
    }

    @Override
    public void delete(List<DataTable> deleteList) {
        System.out.println("es delete");
    }

    @Override
    public void insert(List<DataTable> insertList) {
        System.out.println("es insert");
    }
}
