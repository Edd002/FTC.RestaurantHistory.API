package com.fiap.tech.challenge.domain.menuitemorder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemOrderResponseDTO {
    private String name;
    private String description;
    private BigDecimal priceAtOrder;
    private Integer quantity;
}
