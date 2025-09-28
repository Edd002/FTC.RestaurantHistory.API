package com.fiap.tech.challenge.messaging.consumer;

import com.fiap.tech.challenge.domain.order.dto.OrderMessageDTO;
import com.fiap.tech.challenge.domain.order.enumerated.OrderStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderConsumer {

    @KafkaListener(topics = "${spring.kafka.topic.name.order-topic}", groupId = "{spring.kafka.consumer.order.group-id}", containerFactory = "orderKafkaListenerContainerFactory")
    public void listenToOrderTopic(ConsumerRecord<String, OrderMessageDTO> message, Acknowledgment ack) {

        try {
            log.info("Received Message Partition " + message.partition());
            log.info("Received Message Json" + message.value());
            OrderMessageDTO order = message.value();

            log.info("Pedido recebido: {}", order);
            ack.acknowledge();
        } catch (Exception e) {
            log.error("Erro ao processar o pedido: {}", e.getMessage());
        }
    }

    private void notifyOrderUpdate(OrderMessageDTO order) {
        if(OrderStatusEnum.REQUESTED.equals(order.getStatus())) {
            log.info("Pedido {} criado.", order.getHashId());
        }
        if(OrderStatusEnum.CONFIRMED.equals(order.getStatus())) {
            log.info("Pedido {} confirmado. Iniciando preparo.", order.getHashId());
        }
        if(OrderStatusEnum.WAITING_FOR_PICKUP.equals(order.getStatus())) {
            log.info("Pedido {} aguardando retirada.", order.getHashId());
        }
    }
}
