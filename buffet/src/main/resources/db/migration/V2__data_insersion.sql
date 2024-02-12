INSERT INTO User_Type (id_user_type, user_type) VALUES    (1, 'Administrator'),
                                                          (2, 'Worker'),
                                                          (3, 'Public');
INSERT INTO Status
VALUES     (1, 'enable'),
           (2, 'disabled'),
           (3, 'disabled'),
           (4, 'accepted'),
           (5, 'sold'),
           (6, 'required');
INSERT INTO User_Info (name, lastname,phone, created_at, modified_at, fk_user_type) VALUES ('Christian', 'Alonso','7778189101', '2024-02-11 19:35:36', '2024-02-11 19:35:36', '1');
INSERT INTO User_Info (name, lastname,phone, created_at, modified_at, fk_user_type) VALUES ('Victor', 'Noe','777213101', '2024-02-11 19:35:36', '2024-02-11 19:35:36', '2');
INSERT INTO User_Info (name, lastname,phone, created_at, modified_at, fk_user_type) VALUES ('Karel', 'Mendoza','77712121', '2024-02-11 19:35:36', '2024-02-11 19:35:36', '3');

INSERT INTO User_Account (email, password, token,login_time,logout_time, fk_user_info, fk_status   ) VALUES ('c@gmail.com', 'Buffet1!', 'admin','2024-02-11 19:35:36','2024-02-11 19:35:36', '1', '1');
INSERT INTO User_Account (email, password, token,login_time,logout_time, fk_user_info, fk_status   ) VALUES ('v@gmail.com', 'Buffet1!', 'worker','2024-02-11 19:35:36','2024-02-11 19:35:36', '2', '1');
INSERT INTO User_Account (email, password, token,login_time,logout_time, fk_user_info, fk_status   ) VALUES ('k@gmail.com', 'Buffet1!', 'public','2024-02-11 19:35:36','2024-02-11 19:35:36', '3', '1');

