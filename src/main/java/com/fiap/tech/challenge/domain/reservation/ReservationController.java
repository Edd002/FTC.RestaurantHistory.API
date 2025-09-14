package com.fiap.tech.challenge.domain.reservation;

import com.fiap.tech.challenge.domain.reservation.dto.ReservationRequestDTO;
import com.fiap.tech.challenge.domain.reservation.dto.ReservationResponseDTO;
import com.fiap.tech.challenge.global.search.filter.Connection;
import com.fiap.tech.challenge.global.search.filter.PageFilterInput;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;


@Controller
public class ReservationController {

    private final ReservationServiceGateway service;

    public ReservationController(ReservationServiceGateway service) {
        this.service = service;
    }

    @QueryMapping
    public Connection<ReservationResponseDTO> getReservations(@Argument ReservationRequestDTO filter, @Argument PageFilterInput page) {
        return service.getReservations(filter, page);
    }
}
