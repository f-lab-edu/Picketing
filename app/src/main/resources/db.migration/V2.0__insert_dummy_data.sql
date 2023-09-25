INSERT INTO `user` (email, password, name, phone_number, created_at)
VALUES ('asdf@gmail.com', 'EWo5?y83', 'memberA', '010-1234-5678', now());
INSERT INTO `user` (email, password, name, phone_number, created_at)
VALUES ('test1@gmail.com', 'EWo5?y83', 'memberB', '010-5678-9012', now());
INSERT INTO `user` (email, password, name, phone_number, created_at)
VALUES ('test2@gmail.com', 'EWo5?y83', 'memberC', '010-2345-6789', now());

INSERT INTO `show` (title, genre, sub_genre, start_date, end_date, venue, running_time, intermission, age_group,
                    details, is_bookable, owner_id)
VALUES ('Charlie Puth', 'CONCERT', 'FOREIGN', CURRENT_DATE, CURRENT_DATE, 'KSPO DOME', '110', '0', 'FIFTHTEEN',
        '찰리푸스 내한 공연 - 서울', 1, 1);
INSERT INTO `show` (title, genre, sub_genre, start_date, end_date, venue, running_time, intermission, age_group,
                    details, is_bookable, owner_id)
VALUES ('Sam Smith', 'CONCERT', 'FOREIGN', CURRENT_DATE, CURRENT_DATE, 'KSPO DOME', '120', '0', 'NINETEEN',
        '샘 스미스 in 서울', 1, 1);
INSERT INTO `show` (title, genre, sub_genre, start_date, end_date, venue, running_time, intermission, age_group,
                    details, is_bookable, owner_id)
VALUES ('임영웅 IMHERO - 서울', 'CONCERT', 'DOMESTIC', CURRENT_DATE, CURRENT_DATE, 'KSPO DOME', '150', '0', 'SEVEN',
        'IMHERO in Seoul', 1, 1);

INSERT INTO `seat` (show_id)
VALUES (1);
INSERT INTO `seat` (show_id)
VALUES (1);
INSERT INTO `seat` (show_id)
VALUES (1);
INSERT INTO `seat` (show_id)
VALUES (1);

INSERT INTO `seat` (show_id)
VALUES (2);
INSERT INTO `seat` (show_id)
VALUES (2);
INSERT INTO `seat` (show_id)
VALUES (2);

INSERT INTO `seat` (show_id)
VALUES (3);
INSERT INTO `seat` (show_id)
VALUES (3);
INSERT INTO `seat` (show_id)
VALUES (3);
INSERT INTO `seat` (show_id)
VALUES (3);

INSERT
INTO `seat_grade` (title, price, seat_id)
values ('VIP', 160000, 1);
INSERT
INTO `seat_grade` (title, price, seat_id)
values ('S', 154000, 2);
INSERT
INTO `seat_grade` (title, price, seat_id)
values ('R', 140000, 3);
INSERT
INTO `seat_grade` (title, price, seat_id)
values ('A', 100000, 4);

INSERT
INTO `seat_grade` (title, price, seat_id)
values ('VIP', 240000, 5);
INSERT
INTO `seat_grade` (title, price, seat_id)
values ('S', 220000, 6);
INSERT
INTO `seat_grade` (title, price, seat_id)
values ('R', 190000, 7);

INSERT
INTO `seat_grade` (title, price, seat_id)
values ('VIP', 165000, 8);
INSERT
INTO `seat_grade` (title, price, seat_id)
values ('S', 145000, 9);
INSERT
INTO `seat_grade` (title, price, seat_id)
values ('R', 125000, 10);
INSERT
INTO `seat_grade` (title, price, seat_id)
values ('A', 100000, 11);
