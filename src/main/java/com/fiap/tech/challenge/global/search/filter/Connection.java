package com.fiap.tech.challenge.global.search.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Connection<T> {
    private List<Edge<T>> edges;
    private PageInfo pageInfo;
}
