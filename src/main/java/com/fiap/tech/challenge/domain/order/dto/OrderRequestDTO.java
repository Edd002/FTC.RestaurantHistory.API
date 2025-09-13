package com.fiap.tech.challenge.domain.order.dto;

import com.fiap.tech.challenge.domain.order.enumerated.OrderStatusEnum;
import com.fiap.tech.challenge.domain.order.enumerated.OrderTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SchemaMapping("OrderFilterInput")
public class OrderRequestDTO {
    private OrderStatusEnum status;
    private OrderTypeEnum type;
    private String restaurantName;
    private String userName;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
