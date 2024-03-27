INSERT INTO user_type (id_user_type, type_name) VALUES
                                                    (X'2765C5E1D83F11EE944BD812655E2906', 'Administrator'),
                                                    (X'2765C5E1D83F11EE944BD812655E2907', 'Worker'),
                                                    (X'2765C5E1D83F11EE944BD812655E2908', 'Public');

INSERT INTO status (id_status, status_name,status_description) VALUES
                                                (X'2765C5E1D83F11EE944BD812655E2909', 'enable','to_user'),
                                                (X'2765C5E1D83F11EE944BD812655E2910', 'disabled','to_user'),
                                                (X'2765C5E1D83F11EE944BD812655E2809', 'enable','to_package'),
                                                (X'2765C5E1D83F11EE944BD812655E2810', 'disabled','to_package'),
                                                (X'2765C5E1D83F11EE944BD812655E2709', 'enable','to_worker'),
                                                (X'2765C5E1D83F11EE944BD812655E2710', 'disabled','to_worker'),
                                                (X'2765C5E1D83F11EE944BD812655E2911', 'accepted','to_order'),
                                                (X'2765C5E1D83F11EE944BD812655E2811', 'process','to_order'),
                                                (X'2765C5E1D83F11EE944BD812655E2912', 'sold','to_order'),
                                                (X'2765C5E1D83F11EE944BD812655E2913', 'required','to_order');
INSERT INTO user_info (id_user_info, name, lastname, surname, phone, created_at, modified_at, fk_user_type) VALUES
                                                                                                                (X'2765C5E1D83F11EE944BD812655E2914', 'Christian', 'Carrasco', 'Alonso', '123456789', NOW(), NULL, X'2765C5E1D83F11EE944BD812655E2906'),
                                                                                                                (X'2765C5E1D83F11EE944BD812655E2915', 'Victor', 'Flores', 'Salgado', '987654321', NOW(), NULL, X'2765C5E1D83F11EE944BD812655E2907'),
                                                                                                                (X'2765C5E1D83F11EE944BD812655E2916', 'Karel', 'Flores', 'Salinas', '987654321', NOW(), NULL, X'2765C5E1D83F11EE944BD812655E2908');

INSERT INTO user_account (id_user_account, email, user_password, token,locked_user, login_time, logout_time, fk_user_info, fk_status) VALUES
                                                                                                                              (X'2765C5E1D83F11EE944BD812655E2918', 'c@gmail.com', '$2a$10$gQZpuhnQLen9pY22v93HKu67T7G4sYfzieWakWrbY1AD7P70BlQ42', 'admin123',true, NOW(), NULL, X'2765C5E1D83F11EE944BD812655E2914', X'2765C5E1D83F11EE944BD812655E2909'),
                                                                                                                              (X'2765C5E1D83F11EE944BD812655E2919', 'k@gmail.com', '$2a$10$gQZpuhnQLen9pY22v93HKu67T7G4sYfzieWakWrbY1AD7P70BlQ42', 'public123',true, NOW(), NULL, X'2765C5E1D83F11EE944BD812655E2916', X'2765C5E1D83F11EE944BD812655E2909');
INSERT INTO address (id_address, street, district , postal_code, city, comments,created_at, fk_user_account) VALUES
                                                                                                     (X'2765C5E1D83F11EE944BD812655E2916', 'Calle Clavel', 'La matona', 12345, 'Cuernavaca', 'Llama antes de llegar',NOW(), X'2765C5E1D83F11EE944BD812655E2919'),
                                                                                                     (X'2765C5E1D83F11EE944BD812655E2917', 'Calle vela', 'Flores Magon', 54321, 'Temixco', 'Llamame', NOW(),X'2765C5E1D83F11EE944BD812655E2919');

INSERT INTO service_category
VALUES      (X'2765C5E1D83F11EE944BD812655E2920', 'Comida'),
            (X'2765C5E1D83F11EE944BD812655E2921', 'Transporte'),
            (X'2765C5E1D83F11EE944BD812655E2922', 'Eventos'),
            (X'2765C5E1D83F11EE944BD812655E2923', 'Utileria');

INSERT INTO service_package
VALUES
    (X'2765C5E1D83F11EE944BD812655E2924','Cena de graduacion','Cena de 2 tiempos, incluye mesas, sillas, meseros, para 10 personas',1000.00,0.00,1,'2024-03-01 22:46:49','2024-03-01 22:46:49',X'2765C5E1D83F11EE944BD812655E2920',X'2765C5E1D83F11EE944BD812655E2809'),
    (X'2765C5E1D83F11EE944BD812655E2925','Urban','Camioneta para 8 personas ',1000.00,0.00,1,'2024-03-01 22:46:49','2024-03-01 22:46:49',X'2765C5E1D83F11EE944BD812655E2921',X'2765C5E1D83F11EE944BD812655E2809'),
    (X'2765C5E1D83F11EE944BD812655E2926','Comida infantil','Cena de 3 tiempos,incluye mesa de dulces, incluye mesas, sillas, meseros, para 30 personas',1000.00,0.00,1,'2024-03-01 22:46:49','2024-03-01 22:46:49',X'2765C5E1D83F11EE944BD812655E2920',X'2765C5E1D83F11EE944BD812655E2809'),
    (X'2765C5E1D83F11EE944BD812655E2927','Taquiza','Incluye tacos de pastor, suadero, costilla, verdura, para 50 personas',1000.00,0.00,1,'2024-03-01 22:46:49','2024-03-01 22:46:49',X'2765C5E1D83F11EE944BD812655E2920',X'2765C5E1D83F11EE944BD812655E2809');

INSERT INTO package_image (id_package_image, image,num_image, fk_service_package) VALUES
                                                                            (X'2765C5E1D83F11EE944BD812655E2928', 'https://www.shutterstock.com/image-photo/party-table-graduation-decoration-600nw-1198731190.jpg',1, X'2765C5E1D83F11EE944BD812655E2924'),
                                                                            (X'2765C5E1D83F11EE944BD812655E2929', 'https://aprende.com/wp-content/uploads/2022/05/bebidas-de-graduacion.jpg',2, X'2765C5E1D83F11EE944BD812655E2924'),
                                                                            (X'2765C5E1D83F11EE944BD812655E2930', 'https://cuponassets.cuponatic-latam.com/backendMx/uploads/imagenes_descuentos/95576/d4ca44bad47c2f27f7584eb40b54752a2414e300.XL2.jpg',3, X'2765C5E1D83F11EE944BD812655E2924'),
                                                                            (X'2765C5E1D83F11EE944BD812655E2931', 'https://horsepowermexico.com/wp-content/uploads/2018/07/P_670a46c74851479788dd70273e709b15.jpg',1, X'2765C5E1D83F11EE944BD812655E2925'),
                                                                            (X'2765C5E1D83F11EE944BD812655E2932', 'https://picturecarsmx.com/wp-content/uploads/2019/12/Mercedes-sprinter-blanca-20-pasajeros-en-renta-en-cdmx-2.jpg',2, X'2765C5E1D83F11EE944BD812655E2925'),
                                                                            (X'2765C5E1D83F11EE944BD812655E2933', 'https://vansmanzahnos.com.mx/wp-content/uploads/2017/07/3bann.png',3, X'2765C5E1D83F11EE944BD812655E2925'),
                                                                            (X'2765C5E1D83F11EE944BD812655E2934', 'https://www.gob.mx/cms/uploads/article/main_image/114673/Depositphotos_122576538_XL_750x392.jpg',1, X'2765C5E1D83F11EE944BD812655E2926'),
                                                                            (X'2765C5E1D83F11EE944BD812655E2935', 'https://lataquiza.com.mx/wp-content/uploads/2021/04/gal2_inf.png',2, X'2765C5E1D83F11EE944BD812655E2926'),
                                                                            (X'2765C5E1D83F11EE944BD812655E2936', 'https://www.estrellasdelarecreacion.com/ibague/images/carretas-7.jpg',3, X'2765C5E1D83F11EE944BD812655E2926'),
                                                                            (X'2765C5E1D83F11EE944BD812655E2937', 'https://taquizasdonjulio.com.mx/wp-content/uploads/2022/01/Taquizas-6.jpg',1, X'2765C5E1D83F11EE944BD812655E2927'),
                                                                            (X'2765C5E1D83F11EE944BD812655E2938', 'https://www.banquetesytaquizas.com.mx/imgagenes/galeria/guisados0.jpg',2, X'2765C5E1D83F11EE944BD812655E2927'),
                                                                            (X'2765C5E1D83F11EE944BD812655E2939', 'https://mlmnyz2kljyg.i.optimole.com/w:1200/h:649/q:mauto/f:avif/https://www.doncanijo.com/wp-content/uploads/2021/10/roberto-carlos-roman-don-TS_g_856-CA-unsplash-1.jpg',3, X'2765C5E1D83F11EE944BD812655E2927');

INSERT INTO payment (id_payment,payment_method, transaction_id, transaction_date, transaction_description) VALUES
                                                        (X'2765C5E1D83F11EE944BD812655E2940','Paypal', '80P041923V7849056', NOW(), 'Pago realizado'),
                                                        (X'2765C5E1D83F11EE944BD812655E2941','Papyal', '80P041923V7849057', NOW(), 'Pago realizado');

INSERT INTO package_order (id_package_order, num_order, order_date, order_price, created_at, fk_user_account, fk_service_package, fk_status, fk_payment, fk_address)
VALUES (X'2765C5E1D83F11EE944BD812655E2942', '123ABC', NOW(), 100.00, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2924', X'2765C5E1D83F11EE944BD812655E2913', X'2765C5E1D83F11EE944BD812655E2940', X'2765C5E1D83F11EE944BD812655E2916'),
       (X'2765C5E1D83F11EE944BD812655E2943', '456DEF', NOW(), 150.00, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2925', X'2765C5E1D83F11EE944BD812655E2913', X'2765C5E1D83F11EE944BD812655E2941', X'2765C5E1D83F11EE944BD812655E2917');


INSERT INTO worker (id_worker, num_worker,locked_worker,worker_password, start_hour, end_hour, fk_user_info,fk_status) VALUES
                                                                                                    (X'2765C5E1D83F11EE944BD812655E2944', 'W456',true, '$2a$10$gQZpuhnQLen9pY22v93HKu67T7G4sYfzieWakWrbY1AD7P70BlQ42', '09:00:00', '18:00:00', X'2765C5E1D83F11EE944BD812655E2915',X'2765C5E1D83F11EE944BD812655E2709');

INSERT INTO worker_assignment (id_worker_assignment, fk_worker, fk_package_order, assignment_date,created_at) VALUES
                                                                                                       (X'2765C5E1D83F11EE944BD812655E2945', X'2765C5E1D83F11EE944BD812655E2944', X'2765C5E1D83F11EE944BD812655E2942', NOW(),NOW()),
                                                                                                       (X'2765C5E1D83F11EE944BD812655E2946', X'2765C5E1D83F11EE944BD812655E2944', X'2765C5E1D83F11EE944BD812655E2943', NOW(),NOW());
INSERT INTO package_has_comment (id_package_has_comment, comment_text, comment_point, comment_date, fk_user_account, fk_service_package) VALUES
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2941', 'Excelente servicio', 5, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2924'),
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2942', 'Excelente ambiente', 5, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2924'),
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2943', 'Me agrado', 4, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2924'),
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2944', 'Regular', 2, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2924'),
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2945', 'Excelente servicio', 5, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2924'),
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2946', 'Buen servicio, pero puede mejorar', 3, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2924'),
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2947', 'Excelente servicio', 5, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2925'),
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2948', 'Excelente ambiente', 5, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2925'),
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2949', 'Me agrado', 4, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2925'),
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2940', 'Regular', 2, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2925'),
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2951', 'Excelente servicio', 5, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2925'),
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2952', 'Buen servicio, pero puede mejorar', 3, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2925'),
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2953', 'Excelente servicio', 5, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2926'),
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2954', 'Excelente ambiente', 5, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2926'),
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2955', 'Me agrado', 4, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2926'),
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2957', 'Regular', 2, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2926'),
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2958', 'Excelente servicio', 5, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2926'),
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2959', 'Buen servicio, pero puede mejorar', 3, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2926'),
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2960', 'Excelente servicio', 5, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2927'),
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2961', 'Excelente ambiente', 5, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2927'),
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2962', 'Me agrado', 4, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2927'),
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2963', 'Regular', 2, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2927'),
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2964', 'Excelente servicio', 5, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2927'),
                                                                                                                                             (X'2765C5E1D83F11EE944BD812655E2965', 'Buen servicio, pero puede mejorar', 3, NOW(), X'2765C5E1D83F11EE944BD812655E2919', X'2765C5E1D83F11EE944BD812655E2927');