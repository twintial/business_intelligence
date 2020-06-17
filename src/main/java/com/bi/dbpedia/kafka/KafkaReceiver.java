package com.bi.dbpedia.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bi.dbpedia.kafka.opretion.OpFactory;
import com.bi.dbpedia.model.DataTable;
import com.bi.dbpedia.model.Noun;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 订阅了mysql的增量消费，当mysql中的数据发生变化时候，同时对neo4j和elasticsearch的数据更新
 */
@Slf4j
@Component
public class KafkaReceiver {

    @Autowired
    private OpFactory opFactory;

    @KafkaListener(topics = {"example"})
    public void listen(ConsumerRecord<String, String> record) {
        // System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
//        String value = record.value();
//        Map result = JSONObject.parseObject(value, Map.class);
//
//        String type = result.get("type").toString();
//        List<String> acceptTypes = new ArrayList<>(Arrays.asList("INSERT", "DELETE", "UPDATE"));
//        if (!acceptTypes.contains(type)) {
//            System.out.println(type);
//            return;
//        }
//        // result.forEach((k, v) -> System.out.printf("k:%s,v:%s\n", k, v));
//
//        List newValues = (List) result.get("data");
//        List oldValues = (List) result.get("old");
//
//        List<DataTable> newList = (List<DataTable>) newValues.stream()
//                .map(v -> JSON.parseObject(v.toString(), DataTable.class)).collect(Collectors.toList());
//
//        // insert和delete的时候old为null
//        List<Map<String, String>> oldList = null;
//        if (oldValues != null) {
//            oldList = (List<Map<String, String>>) oldValues.stream()
//                    .map(v -> JSON.parseObject(v.toString(), Map.class)).collect(Collectors.toList());
//        }
//
//        log.info(String.valueOf(newList));
//        log.info(String.valueOf(oldList));
//
//
//        opFactory.getOp(type).op(newList, oldList);
        System.out.println("1");
    }
}
