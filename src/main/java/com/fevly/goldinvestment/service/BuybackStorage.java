package com.fevly.goldinvestment.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BuybackStorage {


    /*@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    // buat consumer di docker dengan

    //docker exec -it  [ID_CONTAINER] kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic java_in_use_topic --from-beginning
    String kafkaTopic = "java_in_use_topic";

    public void send(String data) {

        kafkaTemplate.send(kafkaTopic, data);
    }


    // check consumer group di docker dengan
    //docker exec -it  [ID_CONTAINER]  kafka-consumer-groups.sh -bootstrap-server 127.0.0.1:9092 --list
    @KafkaListener(topics = "java_in_use_topic", groupId = "console-consumer-38236")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo: " + message);
    }
*/
}
