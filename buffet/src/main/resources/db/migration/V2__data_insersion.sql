INSERT INTO user_type (id_user_type, type_name) VALUES    (1, 'Administrator'),
                                                          (2, 'Worker'),
                                                          (3, 'Public');
INSERT INTO status
VALUES     (1, 'enable'),
           (2, 'disabled'),
           (3, 'disabled'),
           (4, 'accepted'),
           (5, 'sold'),
           (6, 'required');
INSERT INTO user_info (name, lastname,surname,phone, created_at, modified_at, fk_user_type) VALUES ('Christian','Carrasco', 'Alonso','7778189101', '2024-02-11 19:35:36', '2024-02-11 19:35:36', '1');
INSERT INTO user_info (name, lastname,surname,phone, created_at, modified_at, fk_user_type) VALUES ('Victor','Flores', 'Arriaga','777213101', '2024-02-11 19:35:36', '2024-02-11 19:35:36', '2');
INSERT INTO user_info (name, lastname,surname,phone, created_at, modified_at, fk_user_type) VALUES ('Karel','Perez', 'Mendoza','77712121', '2024-02-11 19:35:36', '2024-02-11 19:35:36', '3');

INSERT INTO user_account (email, password, token,login_time,logout_time, fk_user_info, fk_status   ) VALUES ('c@gmail.com', 'Buffet1!', 'admin','2024-02-11 19:35:36','2024-02-11 19:35:36', '1', '1');
INSERT INTO user_account (email, password, token,login_time,logout_time, fk_user_info, fk_status   ) VALUES ('v@gmail.com', 'Buffet1!', 'worker','2024-02-11 19:35:36','2024-02-11 19:35:36', '2', '1');
INSERT INTO user_account (email, password, token,login_time,logout_time, fk_user_info, fk_status   ) VALUES ('k@gmail.com', 'Buffet1!', 'public','2024-02-11 19:35:36','2024-02-11 19:35:36', '3', '1');

