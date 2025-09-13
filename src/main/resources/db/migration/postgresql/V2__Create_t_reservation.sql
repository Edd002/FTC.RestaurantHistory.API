CREATE TABLE public.t_reservation
(
    id               int8         NOT NULL,
    created_by       varchar(255) NULL,
    created_in       timestamp(6) NOT NULL,
    hash_id          varchar(255) NOT NULL,
    updated_by       varchar(255) NULL,
    updated_in       timestamp(6) NULL,
    booking_status   varchar(255) NOT NULL,
    booking_time     varchar(255) NOT NULL,
    booking_date     date         NOT NULL,
    booking_quantity int8         NOT NULL,
    restaurant_name  varchar(255) NOT NULL,
    user_name        varchar(255) NOT NULL,
    primary key (id)
);

CREATE SEQUENCE public.sq_reservation START WITH 1 INCREMENT BY 1;

CREATE UNIQUE INDEX T_RESERVATION__HASH_ID_UK ON public.t_reservation (hash_id);
CREATE UNIQUE INDEX T_RESERVATION__B_TIM_AND_B_DAT_UK ON public.t_reservation (booking_time, booking_date);