package com.fiap.tech.challenge.config;

import com.fiap.tech.challenge.domain.menuitemorder.dto.MenuItemOrderResponseDTO;
import com.fiap.tech.challenge.domain.menuitemorder.entity.MenuItemOrder;
import com.fiap.tech.challenge.domain.order.dto.OrderResponseDTO;
import com.fiap.tech.challenge.domain.order.entity.Order;
import com.fiap.tech.challenge.domain.reservation.dto.ReservationResponseDTO;
import com.fiap.tech.challenge.domain.reservation.entity.Reservation;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

        modelMapper.typeMap(Order.class, OrderResponseDTO.class)
                .addMapping(Order::getOrderCreationDate, OrderResponseDTO::setCreatedDate)
                .addMapping(Order::getOrderDeliveryDate, OrderResponseDTO::setDeliveredDate);

        modelMapper.typeMap(MenuItemOrder.class, MenuItemOrderResponseDTO.class)
                .addMapping(src -> src.getMenuItem().getName(), MenuItemOrderResponseDTO::setName)
                .addMapping(src -> src.getMenuItem().getDescription(), MenuItemOrderResponseDTO::setDescription)
                .addMapping(MenuItemOrder::getPriceAtOrder, MenuItemOrderResponseDTO::setPriceAtOrder)
                .addMapping(MenuItemOrder::getQuantity, MenuItemOrderResponseDTO::setQuantity);
    }

    private void configReservationToReservationResponseDTOMapperPresenter(ModelMapper modelMapper) {
        modelMapper.typeMap(Reservation.class, ReservationResponseDTO.class)
                .addMapping(Reservation::getBookingStatus, ReservationResponseDTO::setBookingStatus)
                .addMapping(Reservation::getBookingTime, ReservationResponseDTO::setBookingTime)
                .addMapping(Reservation::getBookingDate, ReservationResponseDTO::setBookingDate)
                .addMapping(Reservation::getBookingQuantity, ReservationResponseDTO::setBookingQuantity)
                .addMapping(Reservation::getRestaurantName, ReservationResponseDTO::setRestaurantName)
                .addMapping(Reservation::getUserName, ReservationResponseDTO::setUserName);
    }

}
