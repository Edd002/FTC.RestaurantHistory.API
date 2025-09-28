package com.fiap.tech.challenge.messaging.consumer.deserializer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.tech.challenge.domain.menuitem.dto.MenuItemMessageDTO;
import com.fiap.tech.challenge.domain.order.dto.OrderMessageDTO;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderMessageDeserializer implements Deserializer<OrderMessageDTO> {

    private final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    public OrderMessageDTO deserialize(String topic, byte[] data) {
        if (data == null) return null;

        try {
            OrderMessageDTO dto = mapper.readValue(data, OrderMessageDTO.class);

            JsonNode root = mapper.readTree(data);

            JsonNode updatedDateNode = root.path("updatedDate");
            if (updatedDateNode.isNumber()) {
                long epochMillis = updatedDateNode.asLong();
                dto.setDeliveredDate(new Date(epochMillis));
            }

            JsonNode menuItemOrdersNode = root.path("menuItemOrders");
            if (menuItemOrdersNode.isArray()) {
                List<MenuItemMessageDTO> items = new ArrayList<>();
                for (JsonNode orderItemNode : menuItemOrdersNode) {
                    JsonNode menuItemNode = orderItemNode.path("menuItem");
                    if (menuItemNode.isObject()) {
                        MenuItemMessageDTO item =
                                mapper.treeToValue(menuItemNode, MenuItemMessageDTO.class);
                        items.add(item);
                    }
                }
                dto.setOrderItems(items);
            }

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
            throw new RuntimeException("Erro ao desserializar OrderMessageDTO", e);
        }
    }
}


