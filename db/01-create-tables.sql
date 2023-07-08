create table if not exists cat_category
(
    deleted          boolean      not null,
    creation_date    timestamp(6) not null,
    id               bigserial
    primary key,
    last_update_date timestamp(6) not null,
    name             varchar(255) not null
    );

alter table cat_category
    owner to backend;

create table if not exists cli_client
(
    deleted          boolean      not null,
    creation_date    timestamp(6) not null,
    id               bigserial
    primary key,
    last_update_date timestamp(6) not null,
    national_id      varchar(11)
    );

alter table cli_client
    owner to backend;

create table if not exists ord_order
(
    deleted          boolean          not null,
    total_price      double precision not null,
    client           bigint           not null
    constraint fk_order_client
    references cli_client,
    creation_date    timestamp(6)     not null,
    id               bigserial
    primary key,
    last_update_date timestamp(6)     not null,
    payment          bigint
    unique,
    status           varchar(255)
    constraint ord_order_status_check
    check ((status)::text = ANY
((ARRAY ['RECEIVED'::character varying, 'PREPARING'::character varying, 'READY'::character varying, 'FINISHED'::character varying])::text[]))
    );

alter table ord_order
    owner to backend;

create table if not exists pay_payment
(
    deleted          boolean      not null,
    creation_date    timestamp(6) not null,
    id               bigserial
    primary key,
    last_update_date timestamp(6) not null,
    pay_order        bigint       not null
    unique
    constraint fk_payment_order
    references ord_order,
    qr_data          varchar(200),
    method           varchar(255) not null
    constraint pay_payment_method_check
    check ((method)::text = 'MERCADO_PAGO_QR_CODE'::text),
    status           varchar(255) not null
    constraint pay_payment_status_check
    check ((status)::text = ANY ((ARRAY ['PENDING'::character varying, 'PAID'::character varying])::text[]))
    );

alter table pay_payment
    owner to backend;

alter table ord_order
    add constraint fk_order_payment
        foreign key (payment) references pay_payment;

create table if not exists prd_product
(
    deleted          boolean          not null,
    price            double precision not null,
    category         bigint           not null
    constraint fk_product_category
    references cat_category,
    creation_date    timestamp(6)     not null,
    id               bigserial
    primary key,
    last_update_date timestamp(6)     not null,
    name             varchar(255)     not null
    );

alter table prd_product
    owner to backend;

create table if not exists ori_order_item
(
    deleted          boolean          not null,
    price            double precision not null,
    creation_date    timestamp(6)     not null,
    id               bigserial
    primary key,
    last_update_date timestamp(6)     not null,
    ori_order        bigint           not null
    constraint fk_order_item_order
    references ord_order,
    product          bigint           not null
    constraint fk_order_item_product
    references prd_product,
    quantity         bigint           not null
    );

alter table ori_order_item
    owner to backend;

