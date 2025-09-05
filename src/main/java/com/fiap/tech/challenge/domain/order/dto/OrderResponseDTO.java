package com.fiap.tech.challenge.domain.order.dto;

import com.fiap.tech.challenge.domain.menuitemorder.dto.MenuItemOrderResponseDTO;
import com.fiap.tech.challenge.domain.order.enumerated.OrderStatusEnum;
import com.fiap.tech.challenge.domain.order.enumerated.OrderTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private OrderStatusEnum status;
    private OrderTypeEnum type;
    private List<MenuItemOrderResponseDTO> orderItems;
    private LocalDateTime createdDate;
    private LocalDateTime deliveredDate;
    private String restaurantName;
    private String userName;
}
