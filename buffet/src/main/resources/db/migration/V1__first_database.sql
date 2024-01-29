
CREATE TABLE IF NOT EXISTS User_Type (
id_user_type INT PRIMARY KEY NOT NULL auto_increment,
user_type VARCHAR(50) NOT NULL
    );



CREATE TABLE IF NOT EXISTS Status(
id_status INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
description VARCHAR(100) NOT NULL);



CREATE TABLE IF NOT EXISTS User_info (
id_user_info INT PRIMARY KEY NOT NULL auto_increment,
 name VARCHAR(20) NOT NULL,
lastname VARCHAR(20) NOT NULL,
created_at DATETIME NOT NULL,
modified_at DATETIME,
fk_user_type int NOT NULL,
FOREIGN KEY (fk_user_type) REFERENCES User_Type(id_user_type)
    );

CREATE TABLE IF NOT EXISTS User_Account (
id_user_account INT PRIMARY KEY NOT NULL auto_increment,
email VARCHAR(30) UNIQUE NOT NULL,
password VARCHAR(100) NOT NULL,
token VARCHAR(100) NOT NULL,
fk_user_info int,
fk_status int,
FOREIGN KEY (fk_user_info) REFERENCES user_info(id_user_info),
FOREIGN KEY (fk_status) REFERENCES Status(id_status)
);

CREATE TABLE IF NOT EXISTS Service_Categories (
id_category INT PRIMARY KEY NOT NULL auto_increment,
category_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Service_Packages (
id_package INT PRIMARY KEY NOT NULL auto_increment,
package_name VARCHAR(100) NOT NULL,
package_description VARCHAR(300),
price int NOT NULL,
discount int,
created_at DATETIME NOT NULL,
modified_at DATETIME,
fk_category int NOT NULL,
fk_status int NOT NULL,
FOREIGN KEY (fk_category) REFERENCES Service_Categories(id_category),
FOREIGN KEY (fk_status) REFERENCES Status(id_status)
);

CREATE TABLE IF NOT EXISTS Payment_Method(
id_payment_method INT PRIMARY KEY NOT NULL auto_increment,
name VARCHAR(100) NOT NULL,
description VARCHAR(100) NOT NULL,
fk_status INT NOT NULL,
FOREIGN KEY (fk_status) REFERENCES status(id_status)
);

CREATE TABLE IF NOT EXISTS Package_Orders (
id_order INT PRIMARY KEY NOT NULL auto_increment,
order_date DATETIME,
order_price int NOT NULL,
fk_user_account int NOT NULL,
fk_package int NOT NULL,
fk_status int NOT NULL,
fk_payment_method int,
FOREIGN KEY (fk_user_account) REFERENCES user_account(id_user_account),
FOREIGN KEY (fk_package) REFERENCES Service_Packages(id_package),
FOREIGN KEY (fk_status) REFERENCES status(id_status),
FOREIGN KEY (fk_payment_method) REFERENCES Payment_Method(id_payment_method)
);
CREATE TABLE IF NOT EXISTS Workers_Assignment (
id_assigment INT PRIMARY KEY NOT NULL auto_increment,
fk_user_account INT NOT NULL,
fk_order int NOT NULL,
FOREIGN KEY (fk_user_account) REFERENCES user_account(id_user_account),
FOREIGN KEY (fk_order) REFERENCES Package_Orders(id_order)
);