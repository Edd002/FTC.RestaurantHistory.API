package com.fiap.tech.challenge.domain.menuitem.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class MenuItemMessageDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
}
