package com.fiap.tech.challenge.global.search.pagination;

import com.fiap.tech.challenge.global.search.filter.Connection;
import com.fiap.tech.challenge.global.search.filter.Edge;
import com.fiap.tech.challenge.global.search.filter.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class GraphQLConnectionBuilder {

    public <E, D> Connection<D> build(
            Page<E> page,
            int pageSize,
            int offset,
            Function<E, D> mapper
    ) {
        List<Edge<D>> edges = new ArrayList<>();

        for (int i = 0; i < Math.min(page.getContent().size(), pageSize); i++) {
            E entity = page.getContent().get(i);
            D dto = mapper.apply(entity);

            String cursor = String.valueOf(offset + i + 1);
            edges.add(new Edge<>(cursor, dto));
        }

        boolean hasNextPage = page.getContent().size() > pageSize;
        boolean hasPreviousPage = offset > 0;

        PageInfo pageInfo = new PageInfo(
                hasNextPage,
                hasPreviousPage,
                edges.isEmpty() ? null : edges.getFirst().getCursor(),
                edges.isEmpty() ? null : edges.getLast().getCursor()
        );

        return new Connection<>(edges, pageInfo);
    }
}

