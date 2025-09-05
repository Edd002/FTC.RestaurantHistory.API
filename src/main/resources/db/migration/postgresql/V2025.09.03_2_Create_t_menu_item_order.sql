CREATE TABLE public.t_menu_item_order
(
    id             int8           NOT NULL PRIMARY KEY,
    created_by     varchar(255) NULL,
    created_in     timestamp(6)   NOT NULL,
    updated_by     varchar(255) NULL,
    updated_in     timestamp(6) NULL,
    hash_id        varchar(255)   NOT NULL,
    order_id       int8           NOT NULL,
    menu_item_id   int8           NOT NULL,
    quantity       int4           NOT NULL,
    price_at_order numeric(10, 2) NOT NULL,
    primary key (id)
);

CREATE SEQUENCE public.sq_menu_item_order START WITH 1 INCREMENT BY 1;

ALTER TABLE public.t_menu_item_order
    ADD CONSTRAINT t_menu_item_order__fk_order
        FOREIGN KEY (order_id) REFERENCES public.t_order (id) ON DELETE CASCADE;

ALTER TABLE public.t_menu_item_order
    ADD CONSTRAINT t_menu_item_order__fk_menu_item
        FOREIGN KEY (menu_item_id) REFERENCES public.t_menu_item (id);


CREATE INDEX idx_menu_item_order_order_id ON public.t_menu_item_order (order_id);
CREATE INDEX idx_menu_item_order_menu_item_id ON public.t_menu_item_order (menu_item_id);