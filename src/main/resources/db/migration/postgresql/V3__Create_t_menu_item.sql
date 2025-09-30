CREATE TABLE public.t_menu_item
(
    id                  int8         NOT NULL,
    created_by          varchar(255) NULL,
    created_in          timestamp(6) NOT NULL,
    hash_id             varchar(255) NOT NULL,
    updated_by          varchar(255) NULL,
    updated_in          timestamp(6) NULL,
    name                varchar(255) NOT NULL,
    description         varchar(255) NOT NULL,
    price               numeric(7,2) NOT NULL,
    quantity            int4         NULL,
    fk_order            int8         NOT NULL,
    primary key (id)
);

CREATE SEQUENCE IF NOT EXISTS public.sq_menu_item START WITH 1 INCREMENT BY 1;

CREATE INDEX IF NOT EXISTS T_MENU_ITEM__HASH_ID_UK ON public.t_menu_item (hash_id);

ALTER TABLE public.t_menu_item
    ADD CONSTRAINT FK_MENU_ITEM_ORDER
        FOREIGN KEY (fk_order)
            REFERENCES public.t_order (id);
