package com.fiap.tech.challenge.domain.menuitemorder.entity;

import com.fiap.tech.challenge.domain.menuitem.entity.MenuItem;
import com.fiap.tech.challenge.domain.order.entity.Order;
import com.fiap.tech.challenge.global.audit.Audit;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_menu_item_order")
public class MenuItemOrder extends Audit implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "fk_order", nullable = false)
    private Order order;

    @ManyToOne(fetch= FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE })
    @JoinColumn(name = "fk_menu_item", nullable = false)
    private MenuItem menuItem;

    private Integer quantity;

    private BigDecimal priceAtOrder;
}
