package com.fiap.tech.challenge.domain.reservation.dto;

import com.fiap.tech.challenge.domain.reservation.enumerated.ReservationBookingTimeEnum;
import com.fiap.tech.challenge.domain.reservation.enumerated.ReservationStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestDTO {
    private ReservationStatusEnum bookingStatus;
    private ReservationBookingTimeEnum bookingTime;
    private String restaurantName;
    private String userName;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
