use picketing;

INSERT INTO `USERS` (email, password, name, phone_number, created_at)
VALUES ('user1@gmail.com',
        'f8b9fd9ea59813a4ec6234f8a546d35b51d1f7bc1665e9fb6ced62ac14fc11c996e3406b9258e2702e1af16e95067cde7b57fa30de17e5b9d1522a41b209e4a7',
        'memberA', '010-1234-5678', now());
INSERT INTO `USERS` (email, password, name, phone_number, created_at)
VALUES ('user2@gmail.com',
        'f8b9fd9ea59813a4ec6234f8a546d35b51d1f7bc1665e9fb6ced62ac14fc11c996e3406b9258e2702e1af16e95067cde7b57fa30de17e5b9d1522a41b209e4a7',
        'memberB', '010-5678-9012', now());
INSERT INTO `USERS` (email, password, name, phone_number, created_at)
VALUES ('user3@gmail.com',
        'f8b9fd9ea59813a4ec6234f8a546d35b51d1f7bc1665e9fb6ced62ac14fc11c996e3406b9258e2702e1af16e95067cde7b57fa30de17e5b9d1522a41b209e4a7',
        'memberC', '010-2345-6789', now());

INSERT INTO `SHOWS` (title, genre, sub_genre, start_date, end_date, venue, running_time, intermission, age_group,
                     details, is_bookable, owner_id, created_at)
VALUES ('Charlie Puth', 'CONCERT', 'FOREIGN', CURRENT_DATE, CURRENT_DATE, 'KSPO DOME', 110, 0, 'FIFTHTEEN',
        '찰리푸스 내한 공연 - 서울', 1, 1, now());
INSERT INTO `SHOWS` (title, genre, sub_genre, start_date, end_date, venue, running_time, intermission, age_group,
                     details, is_bookable, owner_id, created_at)
VALUES ('Sam Smith', 'CONCERT', 'FOREIGN', CURRENT_DATE, CURRENT_DATE, 'KSPO DOME', 120, 0, 'NINETEEN',
        '샘 스미스 in 서울', 1, 1, now());
INSERT INTO `SHOWS` (title, genre, sub_genre, start_date, end_date, venue, running_time, intermission, age_group,
                     details, is_bookable, owner_id, created_at)
VALUES ('임영웅 IMHERO - 서울', 'CONCERT', 'DOMESTIC', CURRENT_DATE, CURRENT_DATE, 'KSPO DOME', 150, 0, 'SEVEN',
        'IMHERO in Seoul', 1, 1, now());

INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (1, now(), 'VIP', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (1, now(), 'SR', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (1, now(), 'R', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (1, now(), 'S', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (1, now(), 'A', now());

INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (2, now(), 'VIP', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (2, now(), 'SR', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (2, now(), 'R', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (2, now(), 'S', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (2, now(), 'A', now());

INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (3, now(), 'VIP', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (3, now(), 'SR', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (3, now(), 'R', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (3, now(), 'S', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (3, now(), 'A', now());
