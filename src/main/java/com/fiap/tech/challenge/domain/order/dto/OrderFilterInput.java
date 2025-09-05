package com.fiap.tech.challenge.domain.order.dto;

import com.fiap.tech.challenge.domain.order.enumerated.OrderStatusEnum;
import com.fiap.tech.challenge.domain.order.enumerated.OrderTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderFilterInput {
    private OrderStatusEnum status;
    private OrderTypeEnum type;
    private String restaurantName;
    private String userName;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
