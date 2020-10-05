create table customer(
    id INTEGER (10) NOT NULL AUTO_INCREMENT,
    name VARCHAR (255) NOT NULL,
    email VARCHAR (255) NOT NULL,
    PRIMARY KEY(id)
);

create table address(
    id INTEGER(10) NOT NULL AUTO_INCREMENT,
    location VARCHAR(255) NOT NULL,
    province VARCHAR(255) NOT NULL,
    id_customer INTEGER(10) NOT NULL,
    primary key (id),
    constraint fk_customer_id foreign key (id_customer) references customer (id)
);