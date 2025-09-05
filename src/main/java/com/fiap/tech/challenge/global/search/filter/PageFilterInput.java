package com.fiap.tech.challenge.global.search.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageFilterInput {
    private Integer first;
    private String after;
}
