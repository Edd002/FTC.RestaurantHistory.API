package com.fiap.tech.challenge.global.search.specification;

import com.fiap.tech.challenge.global.search.enumerated.SearchOperationEnum;
import com.fiap.tech.challenge.global.util.ValidationUtil;
import lombok.extern.java.Log;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Builder genérico para Specifications, sem filtro padrão de deleted.
 */
@Log
public abstract class BasicSpecificationBuilder<
        TYPE,
        SPECIFICATION extends Specification<TYPE>,
        FILTER
        > {

    protected final List<SearchCriteria> withs;
    protected final List<SearchCriteria> secondaryWiths;
    protected final List<SearchCriteria> wheres;

    public BasicSpecificationBuilder() {
        this.withs = new ArrayList<>();
        this.wheres = new ArrayList<>();
        this.secondaryWiths = new ArrayList<>();
    }

    protected BasicSpecificationBuilder<TYPE, SPECIFICATION, FILTER> secondaryWith(
            String key,
            SearchOperationEnum operation,
            Object value
    ) {
        secondaryWiths.add(new SearchCriteria(key, operation, value));
        return this;
    }

    protected BasicSpecificationBuilder<TYPE, SPECIFICATION, FILTER> with(
            String key,
            SearchOperationEnum operation,
            Object value
    ) {
        withs.add(new SearchCriteria(key, operation, value));
        return this;
    }

    protected BasicSpecificationBuilder<TYPE, SPECIFICATION, FILTER> with(
            String key,
            SearchOperationEnum operation,
            Object value,
            Object param
    ) {
        withs.add(new SearchCriteria(key, operation, value, param));
        return this;
    }

    protected BasicSpecificationBuilder<TYPE, SPECIFICATION, FILTER> where(
            String key,
            SearchOperationEnum operation,
            Object value
    ) {
        wheres.add(new SearchCriteria(key, operation, value));
        return this;
    }

    protected BasicSpecificationBuilder<TYPE, SPECIFICATION, FILTER> where(
            String key,
            SearchOperationEnum operation,
            Object value,
            Object param
    ) {
        wheres.add(new SearchCriteria(key, operation, value, param));
        return this;
    }

    protected BasicSpecificationBuilder<TYPE, SPECIFICATION, FILTER> where(
            String key,
            SearchOperationEnum operation
    ) {
        wheres.add(new SearchCriteria(key, operation));
        return this;
    }

    /**
     * Inicializa os critérios a partir do filtro recebido.
     */
    protected abstract void initParams(FILTER filter);

    /**
     * Cria uma Specification com os parâmetros informados.
     */
    protected abstract SPECIFICATION buildSpecification(
            String key,
            SearchOperationEnum operation,
            Object value
    );

    protected abstract SPECIFICATION buildSpecification(
            String key,
            SearchOperationEnum operation,
            Object value,
            Object param
    );

    /**
     * Permite customizar se as cláusulas "with" serão combinadas com OR ou AND.
     */
    protected boolean softFilters() {
        return true;
    }

    /**
     * Cria a Specification final a partir do filtro.
     */
    public Optional<Specification<TYPE>> build(FILTER filter) {
        Optional<Specification<TYPE>> result = buildBasicSpecification(filter);

        if (ValidationUtil.isNotEmpty(secondaryWiths)) {
            if (result.isPresent()) {
                Specification<TYPE> spec = result.get();

                List<SPECIFICATION> listWiths = secondaryWiths.stream()
                        .map(it -> buildSpecification(it.getKey(), it.getOperation(), it.getValue(), it.getParam()))
                        .toList();

                Specification<TYPE> filtersWiths = Specification.where(listWiths.getFirst());
                for (int i = 1; i < listWiths.size(); i++) {
                    SPECIFICATION specification = listWiths.get(i);
                    filtersWiths = filtersWiths.or(specification);
                }

                return Optional.of(spec.and(filtersWiths));
            }
        }
        return result;
    }

    private Optional<Specification<TYPE>> buildBasicSpecification(FILTER filter) {
        initParams(filter);

        List<SPECIFICATION> listWiths = withs.stream()
                .map(it -> buildSpecification(it.getKey(), it.getOperation(), it.getValue(), it.getParam()))
                .toList();

        if (listWiths.isEmpty() && ValidationUtil.isNotEmpty(wheres)) {
            SearchCriteria first = wheres.getFirst();
            Specification<TYPE> filters = Specification.where(
                    buildSpecification(first.getKey(), first.getOperation(), first.getValue(), first.getParam())
            );

            for (int i = 1; i < wheres.size(); i++) {
                SearchCriteria search = wheres.get(i);
                SPECIFICATION specification = buildSpecification(
                        search.getKey(),
                        search.getOperation(),
                        search.getValue(),
                        search.getParam()
                );
                filters = filters.and(specification);
            }
            return Optional.of(filters);
        }

        if (listWiths.isEmpty()) {
            return Optional.empty();
        }

        Specification<TYPE> filtersWiths = Specification.where(listWiths.getFirst());
        for (int i = 1; i < listWiths.size(); i++) {
            SPECIFICATION specification = listWiths.get(i);
            filtersWiths = softFilters()
                    ? filtersWiths.or(specification)
                    : filtersWiths.and(specification);
        }

        if (ValidationUtil.isNotEmpty(wheres)) {
            for (SearchCriteria it : wheres) {
                filtersWiths = filtersWiths.and(
                        buildSpecification(it.getKey(), it.getOperation(), it.getValue(), it.getParam())
                );
            }
        }

        return Optional.of(filtersWiths);
    }
}
