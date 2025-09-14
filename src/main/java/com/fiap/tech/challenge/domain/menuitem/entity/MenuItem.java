package com.fiap.tech.challenge.domain.menuitem.entity;

import com.fiap.tech.challenge.domain.order.entity.Order;
import com.fiap.tech.challenge.global.audit.Audit;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_menu_item")
public class MenuItem extends Audit implements Serializable {

    @Id
    @GeneratedValue(generator = "SQ_MENU_ITEM")
    @SequenceGenerator(name = "SQ_MENU_ITEM", sequenceName = "SQ_MENU_ITEM", schema = "public", allocationSize = 1)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", precision = 7, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "fk_order", nullable = false)
    private Order order;
}
