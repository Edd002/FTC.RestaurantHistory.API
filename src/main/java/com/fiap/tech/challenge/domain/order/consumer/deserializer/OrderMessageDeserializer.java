package com.fiap.tech.challenge.domain.order.consumer.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fiap.tech.challenge.domain.order.dto.OrderMessageDTO;
import com.fiap.tech.challenge.domain.order.enumerated.OrderStatusEnum;
import com.fiap.tech.challenge.domain.order.enumerated.OrderTypeEnum;

import java.io.IOException;
import java.io.Serial;

public class OrderMessageDeserializer extends StdDeserializer<OrderMessageDTO> {
    @Serial
    private static final long serialVersionUID = 1L;

    public OrderMessageDeserializer() {
        super(OrderMessageDTO.class);
    }

    @Override
    public OrderMessageDTO deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode root = mapper.readTree(jp);

        OrderMessageDTO dto = new OrderMessageDTO();

        if (root.has("status")) {
            dto.setStatus(OrderStatusEnum.valueOf(root.get("status").asText()));
        }
        if (root.has("type")) {
            dto.setType(OrderTypeEnum.valueOf(root.get("type").asText()));
        }

//        // lista de itens
//        if (root.has("orderItems")) {
//            JavaType type = mapper.getTypeFactory()
//                    .constructCollectionType(List.class, MenuItemMessageDTO.class);
//            dto.setOrderItems(mapper.convertValue(root.get("orderItems"), type));
//        }

        dto.setCreatedDate(root.path("createdDate").asText(null));
        dto.setDeliveredDate(root.path("deliveredDate").asText(null));
        dto.setUserName(root.path("userName").asText(null));


        JsonNode restaurantNode = root.path("restaurant");
        if (restaurantNode.isObject()) {
            dto.setRestaurantName(restaurantNode.path("name").asText(null));
        }

        JsonNode userNode = root.path("user");
        if (restaurantNode.isObject()) {
            dto.setUserName(userNode.path("name").asText(null));
        }

        return dto;
    }
}
