CREATE TABLE public.t_menu_item
(
    id          int8           NOT NULL PRIMARY KEY,
    created_by   varchar(255)  NULL,
    created_in   timestamp(6)  NOT NULL,
    updated_by          varchar(255) NULL,
    updated_in          timestamp(6) NULL,
    hash_id      varchar(255)  NOT NULL,
    name        varchar(255)   NOT NULL,
    description varchar(255),
    price       numeric(10, 2) NOT NULL,
    primary key (id)
);

CREATE SEQUENCE public.sq_menu_item START WITH 1 INCREMENT BY 1;

CREATE UNIQUE INDEX T_MENU_ITEM__HASH_ID_UK ON public.t_menu_item (hash_id);
CREATE UNIQUE INDEX T_MENU_ITEM__NAME_UK ON public.t_menu_item (name);