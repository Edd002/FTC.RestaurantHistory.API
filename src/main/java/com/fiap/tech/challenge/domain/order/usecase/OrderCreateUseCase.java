package com.fiap.tech.challenge.domain.order.usecase;

import com.fiap.tech.challenge.domain.menuitem.entity.MenuItem;
import com.fiap.tech.challenge.domain.order.dto.OrderMessageDTO;
import com.fiap.tech.challenge.domain.order.entity.Order;
import com.fiap.tech.challenge.global.util.DateUtil;
import lombok.NonNull;

import java.util.List;

public class OrderCreateUseCase {
    private final Order order;

    public OrderCreateUseCase(@NonNull OrderMessageDTO orderMessageDTO, List<MenuItem> menuItems) {
        this.order = buildOrder(orderMessageDTO, menuItems);
    }

    private Order buildOrder(OrderMessageDTO orderMessageDTO, List<MenuItem> menuItems) {
        return new Order(
                orderMessageDTO.getHashId(),
                orderMessageDTO.getStatus(),
                orderMessageDTO.getType(),
                DateUtil.toLocalDateTime(orderMessageDTO.getCreatedDate()),
                DateUtil.toLocalDateTime(orderMessageDTO.getDeliveredDate()),
                orderMessageDTO.getRestaurantName(),
                orderMessageDTO.getUserName(),
                menuItems
        );
    }

    public Order getBuiltedOrder() {
        return this.order;
    }
}
