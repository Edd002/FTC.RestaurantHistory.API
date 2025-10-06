package com.fiap.tech.challenge.messaging.consumer;

import com.fiap.tech.challenge.domain.order.OrderServiceGateway;
import com.fiap.tech.challenge.domain.order.dto.OrderMessageDTO;
import com.fiap.tech.challenge.domain.order.enumerated.OrderStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class OrderConsumer {

    private final OrderServiceGateway service;

    public OrderConsumer(OrderServiceGateway service) {
        this.service = service;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name.order-topic}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "orderKafkaListenerContainerFactory")
    public void listenToOrderTopic(ConsumerRecord<String, OrderMessageDTO> message, Acknowledgment ack) {

        try {
            OrderMessageDTO order = message.value();
            notifyOrder(order);
            service.receiveOrder(order);
            ack.acknowledge();
        } catch (Exception e) {
            log.error("Erro ao processar o pedido: {}", e.getMessage());
            throw e;
        }
    }

    private void notifyOrder(OrderMessageDTO order) {
        OrderStatusEnum status = order.getStatus();
        Date orderDate = switch (order.getStatus()) {
            case REQUESTED -> order.getCreatedDate();
            case DELIVERED -> order.getDeliveredDate();
            default -> null;
        };
        status.log(order.getHashId(), orderDate, log);
    }
}
