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

-- create table users
-- (
--     id              bigserial primary key,
--     username        varchar(36) not null,
--     password        varchar(80) not null,
--     email           varchar(50) unique,
--     created_at      timestamp default current_timestamp,
--     updated_at      timestamp default current_timestamp
-- );
--
-- create table roles
-- (
--     id              bigserial primary key,
--     name            varchar(50) not null,
--     created_at      timestamp default current_timestamp,
--     updated_at      timestamp default current_timestamp
-- );
--
-- create table users_roles
-- (
--     user_id         bigint not null references users (id),
--     role_id         bigint not null references roles (id),
--     created_at      timestamp default current_timestamp,
--     updated_at      timestamp default current_timestamp,
--     primary key     (user_id, role_id)
-- );
--
-- insert into roles (name)
-- values ('ROLE_USER'),
--        ('ROLE_ADMIN');
--
-- insert into users (username, password, email)
-- values ('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
--        ('john', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');
--
-- insert into users_roles (user_id, role_id)
-- values (1, 1),
--        (2, 2);

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

-- insert into orders (user_id, total_price, address, phone)
-- values (1, 200, 'address', '12345');
--
-- insert into order_items (product_id, order_id, quantity, price_per_product, price)
-- values (1, 1, 2, 100, 200);









