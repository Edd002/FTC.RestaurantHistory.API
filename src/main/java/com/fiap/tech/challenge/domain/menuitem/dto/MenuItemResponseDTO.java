package com.fiap.tech.challenge.domain.menuitem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemResponseDTO {
    private String name;
    private String description;
    private BigDecimal price;
}
