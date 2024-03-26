CREATE TABLE IF NOT EXISTS user_type (
    id_user_type binary(16) PRIMARY KEY NOT NULL,
    type_name VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS status(
    id_status binary(16) NOT NULL PRIMARY KEY,
    status_name VARCHAR(100) NOT NULL,
    status_description VARCHAR(100) NOT NULL);


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
    user_password VARCHAR(100) NOT NULL,
    token VARCHAR(100) NOT NULL,
    locked_user boolean,
    login_time DATETIME,
    logout_time DATETIME,
    fk_user_info binary(16),
    fk_status binary(16),
    FOREIGN KEY (fk_user_info) REFERENCES user_info(id_user_info),
    FOREIGN KEY (fk_status) REFERENCES status(id_status)
    );
CREATE TABLE IF NOT EXISTS address (
    id_address binary(16) PRIMARY KEY NOT NULL,
    street VARCHAR(30) NOT NULL,
    district  VARCHAR(30) NOT NULL,
    postal_code INT(8) NOT NULL,
    city VARCHAR(30) NOT NULL,
    comments VARCHAR(100),
    created_at DATETIME NOT NULL,
    fk_user_account binary(16) NOT NULL,
    FOREIGN KEY (fk_user_account) REFERENCES user_account(id_user_account)
    );
CREATE TABLE IF NOT EXISTS service_category (
    id_service_category binary(16) PRIMARY KEY NOT NULL,
    category_name VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS service_package (
    id_service_package binary(16) PRIMARY KEY NOT NULL,
    package_name VARCHAR(100) NOT NULL,
    package_description VARCHAR(300),
    price DOUBLE NOT NULL,
    discount DOUBLE,
    ability INT NOT NULL,
    created_at DATETIME NOT NULL,
    modified_at DATETIME,
    fk_service_category binary(16) NOT NULL,
    fk_status binary(16) NOT NULL,
    FOREIGN KEY (fk_service_category) REFERENCES service_category(id_service_category),
    FOREIGN KEY (fk_status) REFERENCES status(id_status)
    );
CREATE TABLE IF NOT EXISTS package_image (
    id_package_image binary(16) PRIMARY KEY NOT NULL,
    image varchar(200) NOT NULL,
    num_image INT NULL,
    fk_service_package binary(16) NOT NULL,
    FOREIGN KEY (fk_service_package) REFERENCES service_package(id_service_package)
    );
CREATE TABLE IF NOT EXISTS payment(
    id_payment binary(16) PRIMARY KEY NOT NULL,
    payment_method VARCHAR(40) NULL,
    transaction_id VARCHAR(100) NOT NULL,
    transaction_date DATETIME NOT NULL,
    transaction_description VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS package_order (
    id_package_order binary(16) PRIMARY KEY NOT NULL,
    num_order varchar(20) NOT NULL,
    order_date DATETIME,
    order_price DOUBLE NOT NULL,
    created_at DATETIME NOT NULL,
    fk_user_account binary(16) NOT NULL,
    fk_service_package binary(16) NOT NULL,
    fk_status binary(16) NOT NULL,
    fk_payment binary(16),
    fk_address binary(16) NOT NULL,
    FOREIGN KEY (fk_user_account) REFERENCES user_account(id_user_account),
    FOREIGN KEY (fk_service_package) REFERENCES service_package(id_service_package),
    FOREIGN KEY (fk_status) REFERENCES status(id_status),
    FOREIGN KEY (fk_payment) REFERENCES payment(id_payment),
    FOREIGN KEY (fk_address) REFERENCES address(id_address)
    );
CREATE TABLE IF NOT EXISTS worker (
    id_worker binary(16) PRIMARY KEY NOT NULL,
    num_worker VARCHAR(20) NOT NULL,
    locked_worker boolean,
    worker_password VARCHAR(100) NOT NULL,
    start_hour time NOT NULL,
    end_hour time NOT NULL,
    fk_user_info binary(16) NOT NULL,
    fk_status binary(16),
    FOREIGN KEY (fk_status) REFERENCES status(id_status),
    FOREIGN KEY (fk_user_info) REFERENCES user_info(id_user_info)
    );
CREATE TABLE IF NOT EXISTS worker_assignment (
    id_worker_assignment binary(16) PRIMARY KEY NOT NULL,
    fk_worker binary(16) NOT NULL,
    fk_package_order binary(16) NOT NULL,
    assignment_date DATETIME NOT NULL,
    created_at DATETIME NOT NULL,
    FOREIGN KEY (fk_worker) REFERENCES worker(id_worker),
    FOREIGN KEY (fk_package_order) REFERENCES package_order(id_package_order)
    );
CREATE TABLE IF NOT EXISTs package_has_comment(
    id_package_has_comment binary(16) NOT NULL,
    comment_text VARCHAR(50) NOT NULL,
    comment_point int NOT NULL,
    comment_date DATETIME NOT NULL,
    fk_user_account binary(16) not null,
    fk_service_package binary(16) NOT NULL,
    FOREIGN KEY (fk_user_account) REFERENCES user_account(id_user_account),
    FOREIGN KEY (fk_service_package) REFERENCES service_package(id_service_package)
    );