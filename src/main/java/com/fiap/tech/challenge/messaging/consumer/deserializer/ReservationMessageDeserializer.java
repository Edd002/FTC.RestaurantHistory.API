package com.fiap.tech.challenge.messaging.consumer.deserializer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.tech.challenge.domain.reservation.dto.ReservationMessageDTO;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class ReservationMessageDeserializer implements Deserializer<ReservationMessageDTO> {
    private final ObjectMapper mapper;

    public ReservationMessageDeserializer() {
        this.mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));

        this.mapper.setTimeZone(TimeZone.getTimeZone("GMT-3"));
    }

    @Override
    public ReservationMessageDTO deserialize(String topic, byte[] data) {
        if (data == null) return null;

        try {
            ReservationMessageDTO dto = mapper.readValue(data, ReservationMessageDTO.class);

            JsonNode root = mapper.readTree(data);

            JsonNode restaurantNode = root.path("restaurant");
            if (restaurantNode.isObject()) {
                dto.setRestaurantName(restaurantNode.path("name").asText(null));
            }

            JsonNode userNode = root.path("user");
            if (userNode.isObject()) {
                dto.setUserName(userNode.path("name").asText(null));
            }

            return dto;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao desserializar ReservationMessageDTO", e);
        }
    }
}
