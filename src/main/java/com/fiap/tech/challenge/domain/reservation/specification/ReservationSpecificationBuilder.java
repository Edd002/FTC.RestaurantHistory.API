package com.fiap.tech.challenge.domain.reservation.specification;

import com.fiap.tech.challenge.domain.reservation.dto.ReservationRequestDTO;
import com.fiap.tech.challenge.domain.reservation.entity.Reservation;
import com.fiap.tech.challenge.global.search.enumerated.SearchOperationEnum;
import com.fiap.tech.challenge.global.search.specification.BasicSpecificationBuilder;
import com.fiap.tech.challenge.global.search.specification.SearchCriteria;
import com.fiap.tech.challenge.global.util.ValidationUtil;

public class ReservationSpecificationBuilder extends BasicSpecificationBuilder<Reservation, ReservationSpecification, ReservationRequestDTO> {

    @Override
    protected void initParams(ReservationRequestDTO filter) {
        if (filter == null) {
            return;
        }

        if (ValidationUtil.isNotNull(filter.getBookingStatus())) {
            where("bookingStatus", SearchOperationEnum.EQUAL, filter.getBookingStatus());
        }

        if (ValidationUtil.isNotNull(filter.getBookingTime())) {
            where("bookingTime", SearchOperationEnum.EQUAL, filter.getBookingTime());
        }


        if (ValidationUtil.isNotBlank(filter.getRestaurantName())) {
            where("restaurantName", SearchOperationEnum.LIKE, filter.getRestaurantName());
        }

        if (ValidationUtil.isNotBlank(filter.getUserName())) {
            where("userName", SearchOperationEnum.LIKE, filter.getUserName());
        }

        if (ValidationUtil.isNotNull(filter.getFromDate()) && ValidationUtil.isNotNull(filter.getToDate())) {
            where("bookingDate", SearchOperationEnum.BETWEEN, filter.getFromDate(), filter.getToDate());
        } else if (ValidationUtil.isNotNull(filter.getFromDate())) {
            where("bookingDate", SearchOperationEnum.GREATEST, filter.getFromDate());
        } else if (ValidationUtil.isNotNull(filter.getToDate())) {
            where("bookingDate", SearchOperationEnum.LEAST, filter.getToDate());
        }
    }

    @Override
    protected ReservationSpecification buildSpecification(String key, SearchOperationEnum operation, Object value) {
        return new ReservationSpecification(new SearchCriteria(key, operation, value));
    }

    @Override
    protected ReservationSpecification buildSpecification(String key, SearchOperationEnum operation, Object value, Object param) {
        return new ReservationSpecification(new SearchCriteria(key, operation, value, param));
    }
}

