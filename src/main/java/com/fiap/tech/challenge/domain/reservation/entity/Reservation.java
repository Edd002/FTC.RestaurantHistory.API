package com.fiap.tech.challenge.domain.reservation.entity;

import com.fiap.tech.challenge.domain.reservation.enumerated.ReservationBookingTimeEnum;
import com.fiap.tech.challenge.domain.reservation.enumerated.ReservationStatusEnum;
import com.fiap.tech.challenge.global.audit.Audit;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor
@Entity
@Table(name = "t_reservation")
public class Reservation extends Audit implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(generator = "SQ_RESERVATION")
    @SequenceGenerator(name = "SQ_RESERVATION", sequenceName = "SQ_RESERVATION", schema = "public", allocationSize = 1)
    private Long id;
    @Column(name = "booking_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationStatusEnum bookingStatus;
    @Column(name = "booking_time", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationBookingTimeEnum bookingTime;
    @Column(name = "booking_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date bookingDate;
    @Column(name = "booking_quantity", nullable = false)
    private Long bookingQuantity;
    @Column(name = "restaurant_name", nullable = false)
    private String restaurantName;
    @Column(name = "user_name", nullable = false)
    private String userName;

    public Reservation(
            String hashId,
            ReservationStatusEnum bookingStatus,
            ReservationBookingTimeEnum bookingTime,
            Date bookingDate,
            Long bookingQuantity,
            String restaurantName,
            String userName) {
        this.setHashId(hashId);
        this.setBookingStatus(bookingStatus);
        this.setBookingTime(bookingTime);
        this.setBookingDate(bookingDate);
        this.setBookingQuantity(bookingQuantity);
        this.setRestaurantName(restaurantName);
        this.setUserName(userName);
    }

    public Reservation rebuild(@NonNull ReservationStatusEnum bookingStatus) {
        this.setBookingStatus(bookingStatus);
        return this;
    }
}
