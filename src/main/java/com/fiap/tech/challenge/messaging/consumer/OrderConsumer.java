package com.fiap.tech.challenge.messaging.consumer;

import com.fiap.tech.challenge.domain.order.dto.OrderResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderConsumer {

    @KafkaListener(topics = "${spring.kafka.topic.name.producer}", groupId = "group_order", containerFactory = "orderKafkaListenerContainerFactory")
    public void listenToOrderTopic(ConsumerRecord<String, OrderResponseDTO> message, Acknowledgment ack) {

        try {
            OrderResponseDTO order = message.value();

            log.info("Pedido recebido: {}", order);
            ack.acknowledge();
        } catch (Exception e) {
            log.error("Erro ao processar o pedido: {}", e.getMessage());
        }
    }
}
