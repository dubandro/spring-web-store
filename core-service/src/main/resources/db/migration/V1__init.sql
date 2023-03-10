create table categories
(
    id              bigserial primary key,
    title           varchar(255) unique,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);
insert into categories (title)
values
    ('Food'),
    ('Other');

create table products
(
    id              bigserial primary key,
    title           varchar(255),
    category_id     bigint references categories (id),
    price           int,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

insert into products (title, price, category_id)
values ('milk', 80, 1),
       ('bread', 50, 1),
       ('cheese', 190, 1),
       ('meat', 350, 1),
       ('eggs', 90, 1),
       ('apple', 65, 1),
       ('pear', 95, 1),
       ('chicken', 200, 1),
       ('oil', 150, 1),
       ('grape', 120, 1),
       ('banana', 100, 1),
       ('orange', 120, 1),
       ('ketchup', 110, 1),
       ('tea', 200, 1),
       ('coffee', 500, 1),
       ('wine red', 405, 2),
       ('wine white', 395, 2),
       ('wine rose', 400, 2),
       ('strawberry', 180, 1),
       ('fish', 180, 1),
       ('turkey', 235, 1),
       ('sausages', 195, 1);

create table orders
(
    id                  bigserial primary key,
    user_name           varchar(255) not null,
    total_price         int not null,
    address             varchar(255),
    phone               varchar(255),
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);

create table order_items
(
    id                  bigserial primary key,
    product_id          bigint not null references products (id),
    order_id            bigint not null references orders (id),
    quantity            int not null,
    price_per_product   int not null,
    price               int not null,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);

insert into orders (user_name, total_price, address, phone, created_at)
values ('bob', 2756, 'address', '12345', CURRENT_DATE - 1);

insert into order_items (product_id, order_id, quantity, price_per_product, price, created_at)
values (1, 1, 2, 80, 160, CURRENT_DATE - 1),
       (2, 1, 2, 50, 100, CURRENT_DATE - 1),
       (2, 1, 1, 1, 1, CURRENT_DATE - 1),
       (4, 1, 1, 350, 350, CURRENT_DATE - 1),
       (5, 1, 4, 90, 360, CURRENT_DATE - 1),
       (6, 1, 6, 65, 390, CURRENT_DATE - 1),
       (7, 1, 3, 95, 285, CURRENT_DATE - 1),
       (8, 1, 3, 200, 600, CURRENT_DATE - 1),
       (9, 1, 1, 150, 150, CURRENT_DATE - 1),
       (10, 1, 3, 120, 360, CURRENT_DATE - 1);









