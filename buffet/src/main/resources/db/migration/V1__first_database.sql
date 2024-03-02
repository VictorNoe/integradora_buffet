
CREATE TABLE IF NOT EXISTS user_type (
    id_user_type binary(16) PRIMARY KEY NOT NULL,
    type_name VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS status(
    id_status binary(16) NOT NULL PRIMARY KEY,
    status_name VARCHAR(100) NOT NULL);


CREATE TABLE IF NOT EXISTS user_info (
    id_user_info binary(16) PRIMARY KEY NOT NULL ,
    name VARCHAR(20) NOT NULL,
    lastname VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    created_at DATETIME NOT NULL,
    modified_at DATETIME,
    fk_user_type binary(16) NOT NULL,
    FOREIGN KEY (fk_user_type) REFERENCES user_type(id_user_type)
    );

CREATE TABLE IF NOT EXISTS user_account (
    id_user_account binary(16) PRIMARY KEY NOT NULL ,
    email VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    token VARCHAR(100) NOT NULL,
    login_time DATETIME,
    logout_time DATETIME,
    fk_user_info binary(16),
    fk_status binary(16),
    FOREIGN KEY (fk_user_info) REFERENCES user_info(id_user_info),
    FOREIGN KEY (fk_status) REFERENCES status(id_status)
    );

CREATE TABLE IF NOT EXISTS service_category (
    id_category binary(16) PRIMARY KEY NOT NULL,
    category_name VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS service_package (
    id_package binary(16) PRIMARY KEY NOT NULL,
    package_name VARCHAR(100) NOT NULL,
    package_description VARCHAR(300),
    price DOUBLE NOT NULL,
    discount DOUBLE,
    image varchar(200),
    ability INT NOT NULL,
    created_at DATETIME NOT NULL,
    modified_at DATETIME,
    fk_category binary(16) NOT NULL,
    fk_status binary(16) NOT NULL,
    FOREIGN KEY (fk_category) REFERENCES service_category(id_category),
    FOREIGN KEY (fk_status) REFERENCES status(id_status)
    );


CREATE TABLE IF NOT EXISTS payment_method(
    id_payment_method binary(16) PRIMARY KEY NOT NULL,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(100) NOT NULL,
    fk_status binary(16) NOT NULL,
    FOREIGN KEY (fk_status) REFERENCES status(id_status)
    );

CREATE TABLE IF NOT EXISTS package_order (
    id_order binary(16) PRIMARY KEY NOT NULL,
    num_order varchar(20) NOT NULL,
    order_date DATETIME,
    order_price DOUBLE NOT NULL,
    street VARCHAR(30) NOT NULL,
    distric VARCHAR(30) NOT NULL,
    postal_code INT(8) NOT NULL,
    city VARCHAR(30) NOT NULL,
    comments VARCHAR(100),
    fk_user_account binary(16) NOT NULL,
    fk_package binary(16) NOT NULL,
    fk_status binary(16) NOT NULL,
    fk_payment_method binary(16),
    FOREIGN KEY (fk_user_account) REFERENCES user_account(id_user_account),
    FOREIGN KEY (fk_package) REFERENCES service_package(id_package),
    FOREIGN KEY (fk_status) REFERENCES status(id_status),
    FOREIGN KEY (fk_payment_method) REFERENCES payment_method(id_payment_method)
    );
CREATE TABLE IF NOT EXISTS worker_assignment (
    id_assigment binary(16) PRIMARY KEY NOT NULL,
    fk_user_account binary(16) NOT NULL,
    fk_order binary(16) NOT NULL,
    FOREIGN KEY (fk_user_account) REFERENCES user_account(id_user_account),
    FOREIGN KEY (fk_order) REFERENCES package_order(id_order)
    );
CREATE TABLE IF NOT EXISTs package_has_comment(
    id_package_has_comment binary(16) NOT NULL,
    comment_text VARCHAR(50) NOT NULL,
    comment_point int,
    comment_date DATETIME NOT NULL,
    fk_user_account binary(16) not null,
    fk_package binary(16) NOT NULL,
    FOREIGN KEY (fk_user_account) REFERENCES user_account(id_user_account),
    FOREIGN KEY (fk_package) REFERENCES service_package(id_package)
    );
