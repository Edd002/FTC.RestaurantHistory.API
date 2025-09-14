package com.fiap.tech.challenge.domain.order.entity;

import com.fiap.tech.challenge.domain.menuitem.entity.MenuItem;
import com.fiap.tech.challenge.domain.order.enumerated.OrderStatusEnum;
import com.fiap.tech.challenge.domain.order.enumerated.OrderTypeEnum;
import com.fiap.tech.challenge.global.audit.Audit;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_order")
public class Order extends Audit implements Serializable {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderTypeEnum type;

    @Column(name = "order_creation_date", nullable = false)
    private LocalDateTime orderCreationDate;

    @Column(name = "order_delivery_date", nullable = false)
    private LocalDateTime orderDeliveryDate;

    @Column(name = "restaurant_name", nullable = false)
    private String restaurantName;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
    private List<MenuItem> orderItems;
}
