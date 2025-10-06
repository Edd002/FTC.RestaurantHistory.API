package com.fiap.tech.challenge.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.tech.challenge.domain.order.dto.OrderMessageDTO;
import com.fiap.tech.challenge.domain.reservation.dto.ReservationMessageDTO;
import com.fiap.tech.challenge.messaging.consumer.deserializer.OrderMessageDeserializer;
import com.fiap.tech.challenge.messaging.consumer.deserializer.ReservationMessageDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.ExponentialBackOffWithMaxRetries;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@Slf4j
public class KafkaConsumerConfig {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootStrapAddress;

    @Bean
    public ConsumerFactory<String, OrderMessageDTO> orderConsumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapAddress);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        configProps.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, OrderMessageDeserializer.class);

        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "group_order");

        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderMessageDTO> orderKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, OrderMessageDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(this.orderConsumerFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        factory.setCommonErrorHandler(errorHandler());

        return factory;
    }

    @Bean
    public ConsumerFactory<String, ReservationMessageDTO> reservationConsumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapAddress);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        configProps.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, ReservationMessageDeserializer.class);

        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "group_reservation");

        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ReservationMessageDTO> reservationKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ReservationMessageDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(this.reservationConsumerFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        factory.setCommonErrorHandler(errorHandler());

        return factory;
    }

    @Bean
    public DefaultErrorHandler errorHandler() {
        var backOff = new ExponentialBackOffWithMaxRetries(3);
        backOff.setInitialInterval(3000L);
        backOff.setMultiplier(2.0);
        backOff.setMaxInterval(6000L);
        ObjectMapper objectMapper = new ObjectMapper();

        DefaultErrorHandler errorHandler = new DefaultErrorHandler((record, exception) -> {
            if(record.value() == null) {
                log.error("Descartando mensagem por erro de deserialização.");
            } else {
                try {
                    String payloadAsJson = objectMapper.writeValueAsString(record.value());
                    log.error("Descartando mensagem após exceder o número de tentativas. Payload: {}", payloadAsJson);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        }, backOff);

        errorHandler.setRetryListeners((record, ex, deliveryAttempt) -> {
            if(record.value() == null){
                log.warn("Erro de deserialização da mensagem.");
            } else {
                try {
                    String payloadAsJson = objectMapper.writeValueAsString(record.value());
                    log.warn("Tentativa {} falhou para mensagem: {}", deliveryAttempt, payloadAsJson);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return errorHandler;
    }
}
