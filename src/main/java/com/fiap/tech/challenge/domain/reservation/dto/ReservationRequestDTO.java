package com.fiap.tech.challenge.domain.reservation.dto;

import com.fiap.tech.challenge.domain.reservation.enumerated.ReservationBookingTimeEnum;
import com.fiap.tech.challenge.domain.reservation.enumerated.ReservationStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SchemaMapping("ReservationFilterInput")
public class ReservationRequestDTO {
    private ReservationStatusEnum bookingStatus;
    private ReservationBookingTimeEnum bookingTime;
    private String restaurantName;
    private String userName;
    private LocalDate fromDate;
    private LocalDate toDate;
}
