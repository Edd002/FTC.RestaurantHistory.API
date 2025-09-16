package com.fiap.tech.challenge.domain.order.dto;

import com.fiap.tech.challenge.domain.menuitem.dto.MenuItemResponseDTO;
import com.fiap.tech.challenge.domain.order.enumerated.OrderStatusEnum;
import com.fiap.tech.challenge.domain.order.enumerated.OrderTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SchemaMapping("Order")
public class OrderResponseDTO {
    private String hashId;
    private OrderStatusEnum status;
    private OrderTypeEnum type;
    private List<MenuItemResponseDTO> orderItems;
    private String createdDate;
    private String deliveredDate;
    private String restaurantName;
    private String userName;
}
