package com.fiap.tech.challenge.domain.reservation.usecase;

import com.fiap.tech.challenge.domain.reservation.dto.ReservationMessageDTO;
import com.fiap.tech.challenge.domain.reservation.entity.Reservation;
import lombok.NonNull;

public class ReservationUpdateUseCase {
    private final Reservation reservation;

    public ReservationUpdateUseCase(@NonNull Reservation existingReservation, @NonNull ReservationMessageDTO reservationMessageDTO) {
        this.reservation = rebuildReservation(existingReservation, reservationMessageDTO);
    }

    private Reservation rebuildReservation(@NonNull Reservation existingReservation, @NonNull ReservationMessageDTO reservationMessageDTO) {
        return existingReservation.rebuild(reservationMessageDTO.getBookingStatus());
    }

    public Reservation getRebuiltedReservation() {
        return this.reservation;
    }
}
