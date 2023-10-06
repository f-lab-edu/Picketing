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
VALUES ('Charlie Puth', 'CONCERT', 'FOREIGN', DATE_FORMAT('2023-10-20', '%Y-%m-%d'),
        DATE_FORMAT('2023-10-22', '%Y-%m-%d'), 'KSPO DOME', 110, 0, 'FOURTEEN',
        '찰리푸스 내한 공연 - 서울', 1, 1, now());
INSERT INTO `SHOWS` (title, genre, sub_genre, start_date, end_date, venue, running_time, intermission, age_group,
                     details, is_bookable, owner_id, created_at)
VALUES ('Sam Smith', 'CONCERT', 'FOREIGN', DATE_FORMAT('2023-10-17', '%Y-%m-%d'),
        DATE_FORMAT('2023-10-18', '%Y-%m-%d'), 'KSPO DOME', 120, 0, 'NINETEEN',
        '샘 스미스 in 서울', 1, 1, now());
INSERT INTO `SHOWS` (title, genre, sub_genre, start_date, end_date, venue, running_time, intermission, age_group,
                     details, is_bookable, owner_id, created_at)
VALUES ('임영웅 IMHERO - 서울', 'CONCERT', 'DOMESTIC', DATE_FORMAT('2023-10-27', '%Y-%m-%d'),
        DATE_FORMAT('2023-11-05', '%Y-%m-%d'), 'KSPO DOME', 150, 0, 'SEVEN',
        'IMHERO in Seoul', 1, 1, now());

INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (1, DATE_FORMAT('2023-10-20 18:00', '%Y-%m-%d %H:%i'), 'VIP', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (1, DATE_FORMAT('2023-10-20 18:00', '%Y-%m-%d %H:%i'), 'SR', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (1, DATE_FORMAT('2023-10-20 18:00', '%Y-%m-%d %H:%i'), 'R', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (1, DATE_FORMAT('2023-10-20 18:00', '%Y-%m-%d %H:%i'), 'S', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (1, DATE_FORMAT('2023-10-20 18:00', '%Y-%m-%d %H:%i'), 'A', now());

INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (2, DATE_FORMAT('2023-10-17 19:00', '%Y-%m-%d %H:%i'), 'VIP', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (2, DATE_FORMAT('2023-10-17 18:00', '%Y-%m-%d %H:%i'), 'SR', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (2, DATE_FORMAT('2023-10-17 18:00', '%Y-%m-%d %H:%i'), 'R', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (2, DATE_FORMAT('2023-10-17 18:00', '%Y-%m-%d %H:%i'), 'S', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (2, DATE_FORMAT('2023-10-17 18:00', '%Y-%m-%d %H:%i'), 'A', now());

INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (3, DATE_FORMAT('2023-10-27 20:00', '%Y-%m-%d %H:%i'), 'VIP', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (3, DATE_FORMAT('2023-10-27 20:00', '%Y-%m-%d %H:%i'), 'SR', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (3, DATE_FORMAT('2023-10-27 20:00', '%Y-%m-%d %H:%i'), 'R', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (3, DATE_FORMAT('2023-10-27 20:00', '%Y-%m-%d %H:%i'), 'S', now());
INSERT INTO `SCHEDULED_SHOW_SEAT` (show_id, show_date_time, seat_grade, created_at)
VALUES (3, DATE_FORMAT('2023-10-27 20:00', '%Y-%m-%d %H:%i'), 'A', now());
