package com.fiap.tech.challenge.domain.reservation.dto;

import com.fiap.tech.challenge.domain.reservation.enumerated.ReservationBookingTimeEnum;
import com.fiap.tech.challenge.domain.reservation.enumerated.ReservationStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDTO {
    private ReservationStatusEnum bookingStatus;
    private ReservationBookingTimeEnum bookingTime;
    private LocalDate bookingDate;
    private int bookingQuantity;
    private String restaurantName;
    private String userName;
}
