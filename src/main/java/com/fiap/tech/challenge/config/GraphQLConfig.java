package com.fiap.tech.challenge.config;

import graphql.scalars.ExtendedScalars;
import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class GraphQLConfig {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {

        GraphQLScalarType localDate = GraphQLScalarType.newScalar()
                .name("LocalDate")
                .description("Data local no formato dd/MM/yyyy")
                .coercing(new Coercing<LocalDate, String>() {
                    @Override
                    public String serialize(Object dataFetcherResult) {
                        if (dataFetcherResult instanceof LocalDate date) {
                            return date.format(FORMATTER);
                        }
                        throw new CoercingSerializeException("Erro ao serializar: objeto do tipo LocalDate esperado.");
                    }

                    @Override
                    public LocalDate parseValue(Object input) {
                        if (input instanceof String str) {
                            try {
                                return LocalDate.parse(str, FORMATTER);
                            } catch (Exception e) {
                                throw new CoercingParseValueException("Erro: formato inválido, use dd/MM/yyyy.");
                            }
                        }
                        throw new CoercingParseValueException("Erro: valor esperado como string.");
                    }

                    @Override
                    public LocalDate parseLiteral(Object input) {
                        if (input instanceof String str) {
                            try {
                                return LocalDate.parse(str, FORMATTER);
                            } catch (Exception e) {
                                throw new CoercingParseLiteralException("Erro ao interpretar a data literal: formato inválido, use dd/MM/yyyy.");
                            }
                        }
                        throw new CoercingParseLiteralException("Erro ao interpretar a data literal: valor esperado como string.");
                    }
                })
                .build();

        return wiringBuilder -> wiringBuilder
                .scalar(localDate)
                .scalar(ExtendedScalars.GraphQLBigDecimal)
                .scalar(ExtendedScalars.DateTime);
    }
}


