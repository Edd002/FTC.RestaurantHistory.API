package com.fiap.tech.challenge.domain.reservation.dto;

import com.fiap.tech.challenge.domain.reservation.enumerated.ReservationBookingTimeEnum;
import com.fiap.tech.challenge.domain.reservation.enumerated.ReservationStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationMessageDTO {
    public String hashId;
    private ReservationStatusEnum bookingStatus;
    private ReservationBookingTimeEnum bookingTime;
    private Date bookingDate;
    private Long bookingQuantity;
    private String restaurantName;
    private String userName;
}
