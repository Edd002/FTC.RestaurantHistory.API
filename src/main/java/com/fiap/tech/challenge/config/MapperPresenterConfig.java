package com.fiap.tech.challenge.config;

import com.fiap.tech.challenge.domain.menuitem.dto.MenuItemResponseDTO;
import com.fiap.tech.challenge.domain.menuitem.entity.MenuItem;
import com.fiap.tech.challenge.domain.order.dto.OrderResponseDTO;
import com.fiap.tech.challenge.domain.order.entity.Order;
import com.fiap.tech.challenge.domain.reservation.dto.ReservationResponseDTO;
import com.fiap.tech.challenge.domain.reservation.entity.Reservation;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Configuration
public class MapperPresenterConfig {
    @Bean
    public ModelMapper modelMapperPresenter() {
        ModelMapper modelMapperPresenter = new ModelMapper();
        modelMapperPresenter.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true)
                .setDeepCopyEnabled(true)
                .setAmbiguityIgnored(true)
                .setSkipNullEnabled(true);
        configOrderToOrderResponseDTOMapperPresenter(modelMapperPresenter);
        configReservationToReservationResponseDTOMapperPresenter(modelMapperPresenter);
        return modelMapperPresenter;
    }

    private void configOrderToOrderResponseDTOMapperPresenter(ModelMapper modelMapper) {

        Converter<LocalDateTime, String> toStringConverter = ctx -> {
            if (ctx.getSource() == null) return null;
            OffsetDateTime offsetDateTime = ctx.getSource().atOffset(ZoneOffset.UTC);
            return offsetDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        };

        modelMapper.typeMap(Order.class, OrderResponseDTO.class)
                .addMappings(mapper -> {
                    mapper.using(toStringConverter)
                            .map(Order::getOrderCreationDate, OrderResponseDTO::setCreatedDate);
                    mapper.using(toStringConverter)
                            .map(Order::getOrderDeliveryDate, OrderResponseDTO::setDeliveredDate);
                });

        modelMapper.typeMap(MenuItem.class, MenuItemResponseDTO.class)
                .addMapping(MenuItem::getName, MenuItemResponseDTO::setName)
                .addMapping(MenuItem::getDescription, MenuItemResponseDTO::setDescription)
                .addMapping(MenuItem::getPrice, MenuItemResponseDTO::setPrice)
                .addMapping(MenuItem::getQuantity, MenuItemResponseDTO::setQuantity);
    }

    private void configReservationToReservationResponseDTOMapperPresenter(ModelMapper modelMapper) {
        Converter<java.sql.Date, LocalDate> sqlDateToLocalDate = ctx -> {
            if (ctx.getSource() == null) return null;
            return ctx.getSource().toLocalDate();
        };

        modelMapper.typeMap(Reservation.class, ReservationResponseDTO.class)
                .addMapping(Reservation::getBookingStatus, ReservationResponseDTO::setBookingStatus)
                .addMapping(Reservation::getBookingTime, ReservationResponseDTO::setBookingTime)
                .addMappings(mapper -> mapper.using(sqlDateToLocalDate)
                        .map(Reservation::getBookingDate, ReservationResponseDTO::setBookingDate))
                .addMapping(Reservation::getBookingQuantity, ReservationResponseDTO::setBookingQuantity)
                .addMapping(Reservation::getRestaurantName, ReservationResponseDTO::setRestaurantName)
                .addMapping(Reservation::getUserName, ReservationResponseDTO::setUserName);
    }

}
