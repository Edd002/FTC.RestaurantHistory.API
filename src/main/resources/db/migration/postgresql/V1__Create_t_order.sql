CREATE TABLE public.t_order
(
    id                  int8         NOT NULL,
    created_by          varchar(255) NULL,
    created_in          timestamp(6) NOT NULL,
    hash_id             varchar(255) NOT NULL,
    updated_by          varchar(255) NULL,
    updated_in          timestamp(6) NULL,
    status              varchar(255) NOT NULL,
    type                varchar(255) NOT NULL,
    order_creation_date timestamp(6) NOT NULL,
    order_delivery_date timestamp(6) NULL,
    restaurant_name     varchar(255) NOT NULL,
    user_name           varchar(255) NOT NULL,
    primary key (id)
);

CREATE SEQUENCE public.sq_order START WITH 1 INCREMENT BY 1;

CREATE UNIQUE INDEX T_ORDER__HASH_ID_UK ON public.t_order (hash_id);