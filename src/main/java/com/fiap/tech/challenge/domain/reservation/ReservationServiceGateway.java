package com.fiap.tech.challenge.domain.reservation;

import com.fiap.tech.challenge.domain.reservation.dto.ReservationMessageDTO;
import com.fiap.tech.challenge.domain.reservation.dto.ReservationRequestDTO;
import com.fiap.tech.challenge.domain.reservation.dto.ReservationResponseDTO;
import com.fiap.tech.challenge.domain.reservation.entity.Reservation;
import com.fiap.tech.challenge.domain.reservation.specification.ReservationSpecificationBuilder;
import com.fiap.tech.challenge.domain.reservation.usecase.ReservationCreateUseCase;
import com.fiap.tech.challenge.domain.reservation.usecase.ReservationUpdateUseCase;
import com.fiap.tech.challenge.global.search.filter.Connection;
import com.fiap.tech.challenge.global.search.filter.PageFilterInput;
import com.fiap.tech.challenge.global.search.pagination.GraphQLConnectionBuilder;
import com.fiap.tech.challenge.global.search.pagination.GraphQLPageableBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationServiceGateway {

    private final IReservationRepository reservationRepository;
    private final ModelMapper modelMapperPresenter;
    private final GraphQLPageableBuilder pageableBuilder;
    private final GraphQLConnectionBuilder connectionBuilder;

    @Autowired
    public ReservationServiceGateway(IReservationRepository reservationRepository, ModelMapper modelMapperPresenter,
                               GraphQLPageableBuilder pageableBuilder, GraphQLConnectionBuilder connectionBuilder) {
        this.reservationRepository = reservationRepository;
        this.modelMapperPresenter = modelMapperPresenter;
        this.pageableBuilder = pageableBuilder;
        this.connectionBuilder = connectionBuilder;
    }

    public Connection<ReservationResponseDTO> getReservations(ReservationRequestDTO request, PageFilterInput pageFilter) {
        Pageable pageable = pageableBuilder.build(pageFilter);
        Page<Reservation> page = reservationRepository.findAll(
                new ReservationSpecificationBuilder().build(request).orElse(null),
                pageable
        );

        int offset = pageFilter.getAfter() != null ? Integer.parseInt(pageFilter.getAfter()) : 0;
        int pageSize = pageFilter.getFirst() != null ? pageFilter.getFirst() : 10;

        return connectionBuilder.build(page, pageSize, offset,
                entity -> modelMapperPresenter.map(entity, ReservationResponseDTO.class));
    }

    @Transactional
    public void receiveReservation(ReservationMessageDTO reservationDTO) {
        reservationRepository.findByHashId(reservationDTO.getHashId())
                .ifPresentOrElse(
                        existingReservation -> updateReservation(existingReservation, reservationDTO),
                        () -> createReservation(reservationDTO)
                );
    }

    private void createReservation(ReservationMessageDTO reservationDTO) {
        Reservation reservation = new ReservationCreateUseCase(reservationDTO).getBuiltedReservation();
        reservationRepository.saveAndFlush(reservation);
    }

    private void updateReservation(Reservation existingReservation, ReservationMessageDTO reservationDTO) {
        Reservation updatedReservation = new ReservationUpdateUseCase(existingReservation, reservationDTO).getRebuiltedReservation();
        reservationRepository.saveAndFlush(updatedReservation);
    }
}
