package com.fiap.tech.challenge.messaging.consumer;

import com.fiap.tech.challenge.domain.reservation.ReservationServiceGateway;
import com.fiap.tech.challenge.domain.reservation.dto.ReservationMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ReservationConsumer {

    private final ReservationServiceGateway service;

    public ReservationConsumer(ReservationServiceGateway service) {
        this.service = service;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name.reservation-topic}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "reservationKafkaListenerContainerFactory")
    @RetryableTopic(
            backoff = @Backoff(value = 3000L),
            autoCreateTopics = "false",
            include = Exception.class)
    public void listenToReservationTopic(ConsumerRecord<String, ReservationMessageDTO> message, Acknowledgment ack) {
        try {
            ReservationMessageDTO reservation = message.value();

            service.receiveReservation(reservation);
            ack.acknowledge();
        } catch (Exception e) {
            log.error("Erro ao processar reserva: {}", e.getMessage());
            throw e;
        }
    }
}
