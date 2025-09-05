package com.fiap.tech.challenge.global.search.pagination;

import com.fiap.tech.challenge.global.search.filter.PageFilterInput;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;



@Component
public class GraphQLPageableBuilder {

    public Pageable build(PageFilterInput pageFilter) {
        int offset = 0;
        if (pageFilter.getAfter() != null) {
            offset = Integer.parseInt(pageFilter.getAfter());
        }

        int pageSize = pageFilter.getFirst() != null ? pageFilter.getFirst() : 10;

        return PageRequest.of(offset / pageSize, pageSize + 1);
    }
}
