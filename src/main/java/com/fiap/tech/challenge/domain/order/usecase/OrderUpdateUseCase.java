package com.fiap.tech.challenge.domain.order.usecase;

import com.fiap.tech.challenge.domain.order.dto.OrderMessageDTO;
import com.fiap.tech.challenge.domain.order.entity.Order;
import com.fiap.tech.challenge.global.util.DateUtil;
import lombok.NonNull;

public class OrderUpdateUseCase {

    private final Order order;

    public OrderUpdateUseCase(@NonNull Order existingOrder, @NonNull OrderMessageDTO updatedOrderDTO) {
        this.order = rebuildOrder(existingOrder, updatedOrderDTO);
    }

    private Order rebuildOrder(Order existingOrder, OrderMessageDTO updatedOrderDTO) {
        return existingOrder.rebuild(updatedOrderDTO.getStatus(), updatedOrderDTO.getType(), DateUtil.toLocalDateTime(updatedOrderDTO.getDeliveredDate()));
    }

    public Order getRebuiltedOrder() {
        return this.order;
    }
}
