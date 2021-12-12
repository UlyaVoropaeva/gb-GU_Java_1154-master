INSERT INTO USER (id, user_name, last_name, active)
VALUES  (1, 'Name', 'Test1', 1);

INSERT INTO USER (id, user_name, last_name, active)
VALUES  (2, 'John', 'Test2', 1);

INSERT INTO USER (id, password, email, username, name, last_name, active)
VALUES (3, 'Ivan', 'Test3', 1);

INSERT INTO ACCESS (id, email, password) VALUES (1, 'user1@mail.com', '$2a$06$OAPRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm');
INSERT INTO ACCESS (id, email, password) VALUES (2, 'user2@mail.com', '$2a$06$OAPRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm');
INSERT INTO ACCESS (id, email, password) VALUES (3, 'user3@mail.com', '$2a$06$OAPhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm');


INSERT INTO ROLE (role_id, role) VALUES (1, 'ROLE_ADMIN');
INSERT INTO ROLE (role_id, role) VALUES (2, 'ROLE_MANAGER');
INSERT INTO ROLE (role_id, role) VALUES (3, 'ROLE_USER');

INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (3, 2);
