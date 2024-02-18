
CREATE TABLE IF NOT EXISTS user_type (
id_user_type INT PRIMARY KEY NOT NULL auto_increment,
type_name VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS status(
id_status INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
status_name VARCHAR(100) NOT NULL);


CREATE TABLE IF NOT EXISTS user_info (
id_user_info INT PRIMARY KEY NOT NULL auto_increment,
 name VARCHAR(20) NOT NULL,
lastname VARCHAR(20) NOT NULL,
surname VARCHAR(20) NOT NULL,
phone VARCHAR(15) NOT NULL,
created_at DATETIME NOT NULL,
modified_at DATETIME,
fk_user_type int NOT NULL,
FOREIGN KEY (fk_user_type) REFERENCES user_type(id_user_type)
    );

CREATE TABLE IF NOT EXISTS user_account (
id_user_account INT PRIMARY KEY NOT NULL auto_increment,
email VARCHAR(30) UNIQUE NOT NULL,
password VARCHAR(100) NOT NULL,
token VARCHAR(100) NOT NULL,
login_time DATETIME,
logout_time DATETIME,
fk_user_info int,
fk_status int,
FOREIGN KEY (fk_user_info) REFERENCES user_info(id_user_info),
FOREIGN KEY (fk_status) REFERENCES status(id_status)
);

CREATE TABLE IF NOT EXISTS service_category (
id_category INT PRIMARY KEY NOT NULL auto_increment,
category_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS service_package (
id_package INT PRIMARY KEY NOT NULL auto_increment,
package_name VARCHAR(100) NOT NULL,
package_description VARCHAR(300),
price DOUBLE NOT NULL,
discount DOUBLE,
created_at DATETIME NOT NULL,
modified_at DATETIME,
fk_category int NOT NULL,
fk_status int NOT NULL,
FOREIGN KEY (fk_category) REFERENCES service_category(id_category),
FOREIGN KEY (fk_status) REFERENCES status(id_status)
);

CREATE TABLE IF NOT EXISTS payment_method(
id_payment_method INT PRIMARY KEY NOT NULL auto_increment,
name VARCHAR(100) NOT NULL,
description VARCHAR(100) NOT NULL,
fk_status INT NOT NULL,
FOREIGN KEY (fk_status) REFERENCES status(id_status)
);

CREATE TABLE IF NOT EXISTS package_order (
id_order INT PRIMARY KEY NOT NULL auto_increment,
num_order INT NOT NULL,
order_date DATETIME,
order_price DOUBLE NOT NULL,
street VARCHAR(30) NOT NULL,
distric VARCHAR(30) NOT NULL,
postal_code INT(8) NOT NULL,
city VARCHAR(30) NOT NULL,
comments VARCHAR(100),
fk_user_account int NOT NULL,
fk_package int NOT NULL,
fk_status int NOT NULL,
fk_payment_method int,
FOREIGN KEY (fk_user_account) REFERENCES user_account(id_user_account),
FOREIGN KEY (fk_package) REFERENCES service_package(id_package),
FOREIGN KEY (fk_status) REFERENCES status(id_status),
FOREIGN KEY (fk_payment_method) REFERENCES payment_method(id_payment_method)
);
CREATE TABLE IF NOT EXISTS worker_assignment (
id_assigment INT PRIMARY KEY NOT NULL auto_increment,
fk_user_account INT NOT NULL,
fk_order int NOT NULL,
FOREIGN KEY (fk_user_account) REFERENCES user_account(id_user_account),
FOREIGN KEY (fk_order) REFERENCES package_order(id_order)
);