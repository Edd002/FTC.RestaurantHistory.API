package com.fiap.tech.challenge.global.search.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SchemaMapping("PageFilterInput")
public class PageFilterInput {
    private Integer first;
    private String after;
}
