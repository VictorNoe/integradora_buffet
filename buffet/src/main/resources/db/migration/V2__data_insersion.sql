INSERT INTO user_type (id_user_type, type_name) VALUES (X'1530FD96D83F11EE944BD812655E2906', 'Administrator');
INSERT INTO user_type (id_user_type, type_name) VALUES (X'21E60A80D83F11EE944BD812655E2906', 'Worker');
INSERT INTO user_type (id_user_type, type_name) VALUES (X'2765C5E1D83F11EE944BD812655E2906', 'Public');

INSERT INTO status
VALUES     (X'2765C5E1D83F11EE944BD812655E2909', 'enable'),
           (X'2765C5E1D83F11EE944BD812655E2911', 'disabled'),
           (X'2765C5E1D83F11EE944BD812655E2921', 'disabled'),
           (X'2765C5E1D83F11EE944BD812655E2901', 'accepted'),
           (X'2765C5E1D83F11EE944BD812655E2913', 'sold'),
           (X'2765C5E1D83F11EE944BD812655E2943', 'required');

INSERT INTO user_info (id_user_info,name, lastname,surname,phone, created_at, modified_at, fk_user_type) VALUES (X'2765C5E1D83F11EE944BD812655E2911','Christian','Carrasco', 'Alonso','7778189101', '2024-02-11 19:35:36', '2024-02-11 19:35:36', X'2765C5E1D83F11EE944BD812655E2906');
INSERT INTO user_info (id_user_info,name, lastname,surname,phone, created_at, modified_at, fk_user_type) VALUES (X'2765C5E1D83F11EE944BD812655E2922','Victor','Flores', 'Arriaga','777213101', '2024-02-11 19:35:36', '2024-02-11 19:35:36', X'21E60A80D83F11EE944BD812655E2906');
INSERT INTO user_info (id_user_info,name, lastname,surname,phone, created_at, modified_at, fk_user_type) VALUES (X'2765C5E1D83F11EE944BD812655E2933','Karel','Perez', 'Mendoza','77712121', '2024-02-11 19:35:36', '2024-02-11 19:35:36', X'2765C5E1D83F11EE944BD812655E2906');

INSERT INTO user_account (id_user_account,email, password, token,login_time,logout_time, fk_user_info, fk_status   ) VALUES (X'2765C5E1D83F11EE944BD812655E2921','c@gmail.com', 'Buffet1!', 'admin','2024-02-11 19:35:36','2024-02-11 19:35:36', X'2765C5E1D83F11EE944BD812655E2911', X'2765C5E1D83F11EE944BD812655E2909');
INSERT INTO user_account (id_user_account,email, password, token,login_time,logout_time, fk_user_info, fk_status   ) VALUES (X'2765C5E1D83F11EE944BD812655E2922','v@gmail.com', 'Buffet1!', 'worker','2024-02-11 19:35:36','2024-02-11 19:35:36', X'2765C5E1D83F11EE944BD812655E2922', X'2765C5E1D83F11EE944BD812655E2909');
INSERT INTO user_account (id_user_account,email, password, token,login_time,logout_time, fk_user_info, fk_status   ) VALUES (X'2765C5E1D83F11EE944BD812655E2923','k@gmail.com', 'Buffet1!', 'public','2024-02-11 19:35:36','2024-02-11 19:35:36', X'2765C5E1D83F11EE944BD812655E2933', X'2765C5E1D83F11EE944BD812655E2909');

