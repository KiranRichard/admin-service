package com.online.bus.ticket.reservation.admin.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessageForInsert(String message) {
        kafkaTemplate.send("admin-topic-insert", message);
    }

    public void sendMessageForUpdate(String message) {
        kafkaTemplate.send("admin-topic-update", message);
    }

    public void sendMessageForDelete(String message) {
        kafkaTemplate.send("admin-topic-delete", message);
    }

//    public void sendMessage(String message) {
//        kafkaTemplate.send("test-topic", message);
//    }
}
