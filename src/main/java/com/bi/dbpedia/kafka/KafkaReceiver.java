package com.bi.dbpedia.kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KafkaReceiver {

    @KafkaListener(topics = {"example"})
    public void listen(ConsumerRecord<String, String> record) {
        // System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        String value = record.value();
        Map result = JSONObject.parseObject(value, Map.class);
        result.forEach((k, v) -> System.out.printf("K:%s, v:%s\n", k.toString(), v.toString()));
    }
}
