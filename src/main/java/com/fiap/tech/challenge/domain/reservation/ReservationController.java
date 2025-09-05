package com.fiap.tech.challenge.domain.reservation;

import com.fiap.tech.challenge.domain.reservation.dto.ReservationRequestDTO;
import com.fiap.tech.challenge.domain.reservation.dto.ReservationResponseDTO;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ReservationController {

//    private final OrderReservationService service;

//    public ReservationController(OrderReservationService service) {
//        this.service = service;
//    }

    @QueryMapping
    public List<ReservationResponseDTO> getReservations(@Argument ReservationRequestDTO filter) {
//        return service.getReservations(filter);
        return null;
    }
}
