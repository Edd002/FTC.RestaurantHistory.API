package com.fiap.tech.challenge.global.base;

import com.fiap.tech.challenge.global.search.enumerated.SortOrderEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class BasePaginationFilter {

    @Schema(description = "Número da página.", example = "1", defaultValue = "1")
    @Positive(message = "O número da página deve ser positivo.")
    @NotNull(message = "O número da página deve ser informado.")
    protected Integer pageNumber;

    @Schema(description = "Tamanho da página.", example = "50", defaultValue = "50")
    @Positive(message = "O tamanho da página deve ser positivo.")
    @Max(value = 1000, message = "O tamanho da página deve ser menor ou igual a 1000.")
    @NotNull(message = "O tamanho da página deve ser informado.")
    protected Integer pageSize;

    @Schema(description = "Campos para ordenação.", type = "List", example = "id, hashId")
    protected List<String> sortBy;

    @Schema(description = "Ordenação (ascendente ou descendente).", example = "ASC")
    protected SortOrderEnum sortDirection;

    @Schema(description = "Se todos os elementos serão buscados (pode afetar o desempenho da busca).", example = "false", defaultValue = "false")
    protected Boolean all;

    public BasePaginationFilter(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.all = false;
    }
}
