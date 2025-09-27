package com.fiap.tech.challenge.domain.order.consumer;

import com.fiap.tech.challenge.domain.order.dto.OrderMessageDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {
    private static final Logger log = LoggerFactory.getLogger(OrderConsumer.class);

    @Value(value = "${spring.kafka.consumer.order.group-id}")
    private String groupId;

    @Value(value = "${spring.kafka.topic.name.order-topic}")
    private String topic;

    @KafkaListener(topics = "${spring.kafka.topic.name.order-topic}",  groupId = "${spring.kafka.consumer.order.group-id}", containerFactory = "orderkafkaListenerContainerFactory")
    public void listenTopicOrder(ConsumerRecord<String, OrderMessageDTO> record) {
        log.info("Received Message Partition " + record.partition());
        log.info("Received Message Json" + record.value());
    }
}
