INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO USER_ROLE (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANT(name)
VALUES ('Sample Restaurant'),
       ('Invisible');

INSERT INTO MENU(MENU_DATE, RESTARAUNT_ID)
VALUES (now(), 1),
       ('2023-05-01', 2);

INSERT INTO DISH(MENU_ID, DISH_NAME, DISH_PRICE)
VALUES (1, 'Delicious dish', 150),
       (1, 'Hamburger', 200),
       (2, 'Not visible dish', 10);

INSERT INTO VOTE(VOTE_DATE, RESTAURANT_ID, USER_ID)
VALUES (CURRENT_DATE(), 1, 1);

