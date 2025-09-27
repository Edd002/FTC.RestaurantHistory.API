package com.fiap.tech.challenge.domain.order.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fiap.tech.challenge.domain.menuitem.dto.MenuItemMessageDTO;
import com.fiap.tech.challenge.domain.order.consumer.deserializer.OrderMessageDeserializer;
import com.fiap.tech.challenge.domain.order.enumerated.OrderStatusEnum;
import com.fiap.tech.challenge.domain.order.enumerated.OrderTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = OrderMessageDeserializer.class)
public class OrderMessageDTO {
    private OrderStatusEnum status;
    private OrderTypeEnum type;
    private List<MenuItemMessageDTO> orderItems;
    private String createdDate;
    private String deliveredDate;
    private String restaurantName;
    private String userName;
}
