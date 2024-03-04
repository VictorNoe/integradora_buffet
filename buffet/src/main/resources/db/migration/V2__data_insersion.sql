INSERT INTO user_type (id_user_type, type_name) VALUES (X'1530FD96D83F11EE944BD812655E2906', 'Administrator');
INSERT INTO user_type (id_user_type, type_name) VALUES (X'21E60A80D83F11EE944BD812655E2906', 'Worker');
INSERT INTO user_type (id_user_type, type_name) VALUES (X'2765C5E1D83F11EE944BD812655E2906', 'Public');

INSERT INTO status
VALUES     (X'2765C5E1D83F11EE944BD812655E2909', 'enable'),
           (X'2765C5E1D83F11EE944BD812655E2911', 'disabled'),
           (X'2765C5E1D83F11EE944BD812655E2901', 'accepted'),
           (X'2765C5E1D83F11EE944BD812655E2913', 'sold'),
           (X'2765C5E1D83F11EE944BD812655E2943', 'required');

INSERT INTO user_info (id_user_info,name, lastname,surname,phone, created_at, modified_at, fk_user_type) VALUES (X'2765C5E1D83F11EE944BD812655E2911','Christian','Carrasco', 'Alonso','7778189101', '2024-02-11 19:35:36', '2024-02-11 19:35:36', X'1530FD96D83F11EE944BD812655E2906');
INSERT INTO user_info (id_user_info,name, lastname,surname,phone, created_at, modified_at, fk_user_type) VALUES (X'2765C5E1D83F11EE944BD812655E2922','Victor','Flores', 'Arriaga','777213101', '2024-02-11 19:35:36', '2024-02-11 19:35:36', X'21E60A80D83F11EE944BD812655E2906');
INSERT INTO user_info (id_user_info,name, lastname,surname,phone, created_at, modified_at, fk_user_type) VALUES (X'2765C5E1D83F11EE944BD812655E2933','Karel','Perez', 'Mendoza','77712121', '2024-02-11 19:35:36', '2024-02-11 19:35:36', X'2765C5E1D83F11EE944BD812655E2906');

INSERT INTO user_account (id_user_account,email, password, token,login_time,logout_time, fk_user_info, fk_status   ) VALUES (X'2765C5E1D83F11EE944BD812655E2921','c@gmail.com', 'Buffet1!', 'admin','2024-02-11 19:35:36','2024-02-11 19:35:36', X'2765C5E1D83F11EE944BD812655E2911', X'2765C5E1D83F11EE944BD812655E2909');
INSERT INTO user_account (id_user_account,email, password, token,login_time,logout_time, fk_user_info, fk_status   ) VALUES (X'2765C5E1D83F11EE944BD812655E2922','v@gmail.com', 'Buffet1!', 'worker','2024-02-11 19:35:36','2024-02-11 19:35:36', X'2765C5E1D83F11EE944BD812655E2922', X'2765C5E1D83F11EE944BD812655E2909');
INSERT INTO user_account (id_user_account,email, password, token,login_time,logout_time, fk_user_info, fk_status   ) VALUES (X'2765C5E1D83F11EE944BD812655E2923','k@gmail.com', 'Buffet1!', 'public','2024-02-11 19:35:36','2024-02-11 19:35:36', X'2765C5E1D83F11EE944BD812655E2933', X'2765C5E1D83F11EE944BD812655E2909');

INSERT INTO service_category
VALUES      (X'1165C5E1D83F11EE944BD812655E2909', 'Comida'),
            (X'2265C5E1D83F11EE944BD812655E2909', 'Transporte'),
             (X'3365C5E1D83F11EE944BD812655E2909', 'Eventos'),
            (X'4465C5E1D83F11EE944BD812655E2909', 'Utileria');

INSERT INTO service_package
VALUES
    (X'1265C5E1D83F11EE944BD812655E2909','Cena de graduacion','Cena de 2 tiempos, incluye mesas, sillas, meseros, para 10 personas',1000.00,0.00,'https://www.shutterstock.com/image-photo/party-table-graduation-decoration-600nw-1198731190.jpg',10,'2024-03-01 22:46:49','2024-03-01 22:46:49',X'1165C5E1D83F11EE944BD812655E2909',X'2765C5E1D83F11EE944BD812655E2909'),
    (X'1365C5E1D83F11EE944BD812655E2909','Urban','Camioneta para 8 personas ',1000.00,0.00,'https://horsepowermexico.com/wp-content/uploads/2018/07/P_670a46c74851479788dd70273e709b15.jpg',1,'2024-03-01 22:46:49','2024-03-01 22:46:49',X'2265C5E1D83F11EE944BD812655E2909',X'2765C5E1D83F11EE944BD812655E2909'),
    (X'1465C5E1D83F11EE944BD812655E2909','Comida infantil','Cena de 3 tiempos,incluye mesa de dulces, incluye mesas, sillas, meseros, para 30 personas',1000.00,0.00,'https://www.gob.mx/cms/uploads/article/main_image/114673/Depositphotos_122576538_XL_750x392.jpg',4,'2024-03-01 22:46:49','2024-03-01 22:46:49',X'1165C5E1D83F11EE944BD812655E2909',X'2765C5E1D83F11EE944BD812655E2909'),
    (X'1565C5E1D83F11EE944BD812655E2909','Taquiza','Incluye tacos de pastor, suadero, costilla, verdura, para 50 personas',1000.00,0.00,'https://taquizasdonjulio.com.mx/wp-content/uploads/2022/01/Taquizas-6.jpg',5,'2024-03-01 22:46:49','2024-03-01 22:46:49',X'1165C5E1D83F11EE944BD812655E2909',X'2765C5E1D83F11EE944BD812655E2909');
