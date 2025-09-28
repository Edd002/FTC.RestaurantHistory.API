package com.fiap.tech.challenge.domain.reservation.usecase;

import com.fiap.tech.challenge.domain.reservation.dto.ReservationMessageDTO;
import com.fiap.tech.challenge.domain.reservation.entity.Reservation;
import lombok.NonNull;

public class ReservationCreateUseCase {
    private final Reservation reservation;

    public ReservationCreateUseCase(@NonNull ReservationMessageDTO reservationDTO) {
        this.reservation = buildReservation(reservationDTO);
    }

    private Reservation buildReservation(@NonNull ReservationMessageDTO reservationDTO) {
        return new Reservation(
                reservationDTO.getHashId(),
                reservationDTO.getBookingStatus(),
                reservationDTO.getBookingTime(),
                reservationDTO.getBookingDate(),
                reservationDTO.getBookingQuantity(),
                reservationDTO.getRestaurantName(),
                reservationDTO.getUserName()
        );
    }

    public Reservation getBuiltedReservation() {
        return this.reservation;
    }
}
