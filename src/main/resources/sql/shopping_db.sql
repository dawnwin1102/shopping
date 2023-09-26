-- tb_meal_item definition

CREATE TABLE tb_meal_item (id integer not null, create_by varchar(20) default 25 not null, create_time datetime, description varchar(255), img_url varchar(255), inventory integer, name varchar(255), unit_price numeric(19,2), update_by varchar(20) default 25 not null, update_time datetime, primary key (id));
CREATE UNIQUE INDEX idx_name ON
tb_meal_item (name);

-- tb_order definition

CREATE TABLE tb_order (id integer not null, address varchar(255), contact_mobile varchar(255), create_by varchar(255), create_time datetime, email varchar(255), order_number integer, order_status varchar(255), pay_time datetime, total_amount numeric(19,2), transaction_no varchar(255), update_by varchar(255), update_time datetime, user_name varchar(255), primary key (id));
CREATE UNIQUE INDEX idx_order_number ON tb_order (order_number);


-- tb_order_meal definition

CREATE TABLE tb_order_meal (id integer not null, create_by varchar(255), create_time datetime, meal_id integer, order_id integer, quantity integer, total_amount numeric(19,2), unit_price numeric(19,2), update_by varchar(255), update_time datetime, primary key (id));


-- users definition

CREATE TABLE users (id  integer, name varchar(255), primary key (id));