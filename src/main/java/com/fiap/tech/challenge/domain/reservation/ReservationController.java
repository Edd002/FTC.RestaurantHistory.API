package com.fiap.tech.challenge.domain.reservation;

import com.fiap.tech.challenge.domain.reservation.dto.ReservationRequestDTO;
import com.fiap.tech.challenge.domain.reservation.dto.ReservationResponseDTO;
import org.springframework.graphql.data.method.annotation.Argument;

import java.util.List;


//TODO Finalizar estrutura de reservation
//@Controller
public class ReservationController {

//    private final OrderReservationService service;

//    public ReservationController(OrderReservationService service) {
//        this.service = service;
//    }

//    @QueryMapping
    public List<ReservationResponseDTO> getReservations(@Argument ReservationRequestDTO filter) {
//        return service.getReservations(filter);
        return null;
    }
}
