package com.fiap.tech.challenge.domain.menuitem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SchemaMapping("MenuItem")
public class MenuItemResponseDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
}
