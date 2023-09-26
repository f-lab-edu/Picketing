USE
`picketing`;

INSERT INTO DATE_SCHEDULE (show_id, name, start_date, end_date, created_at)
VALUES (1, '9월 1일', '2023-09-01', '2023-09-01', now());

INSERT INTO DATE_SCHEDULE (show_id, name, start_date, end_date, created_at)
VALUES (1, '9월 2일', '2023-09-02', '2023-09-02', now());

INSERT INTO DATE_SCHEDULE (show_id, name, start_date, end_date, created_at)
VALUES (1, '9월 3일', '2023-09-03', '2023-09-03', now());

INSERT INTO DATE_SCHEDULE (show_id, name, start_date, end_date, created_at)
VALUES (1, '9월 4일', '2023-09-04', '2023-09-04', now());

INSERT INTO DATE_SCHEDULE (show_id, name, start_date, end_date, created_at)
VALUES (2, '10월 17일', '2023-10-17', '2023-10-17', now());

INSERT INTO DATE_SCHEDULE (show_id, name, start_date, end_date, created_at)
VALUES (2, '10월 18일', '2023-10-18', '2023-10-18', now());

INSERT INTO DATE_SCHEDULE (show_id, name, start_date, end_date, created_at)
VALUES (3, '10월 27일', '2023-10-27', '2023-10-27', now());

INSERT INTO DATE_SCHEDULE (show_id, name, start_date, end_date, created_at)
VALUES (3, '10월 28일', '2023-10-28', '2023-10-28', now());

INSERT INTO DATE_SCHEDULE (show_id, name, start_date, end_date, created_at)
VALUES (3, '11월 05일', '2023-11-05', '2023-11-05', now());

INSERT INTO DATE_SCHEDULE (show_id, name, start_date, end_date, created_at)
VALUES (3, '11월 06일', '2023-11-06', '2023-11-06', now());

INSERT INTO DATE_SCHEDULE (show_id, name, start_date, end_date, created_at)
VALUES (3, '11월 07일', '2023-11-07', '2023-11-07', now());

INSERT INTO TIME_SCHEDULE (date_schedule_id, name, start_time, end_time, sequence, created_at)
VALUES (1, '18:00', '18:00:00', '20:00:00', 1, now());

INSERT INTO TIME_SCHEDULE (date_schedule_id, name, start_time, end_time, sequence, created_at)
VALUES (2, '18:00', '18:00:00', '20:00:00', 1, now());

INSERT INTO TIME_SCHEDULE (date_schedule_id, name, start_time, end_time, sequence, created_at)
VALUES (3, '18:00', '18:00:00', '20:00:00', 1, now());

INSERT INTO TIME_SCHEDULE (date_schedule_id, name, start_time, end_time, sequence, created_at)
VALUES (4, '17:00', '17:00:00', '19:00:00', 1, now());

INSERT INTO TIME_SCHEDULE (date_schedule_id, name, start_time, end_time, sequence, created_at)
VALUES (5, '20:30', '20:30:00', '22:30:00', 1, now());

INSERT INTO TIME_SCHEDULE (date_schedule_id, name, start_time, end_time, sequence, created_at)
VALUES (6, '20:00', '20:00:00', '22:00:00', 1, now());

INSERT INTO TIME_SCHEDULE (date_schedule_id, name, start_time, end_time, sequence, created_at)
VALUES (7, '16:00', '16:00:00', '17:30:00', 1, now());

INSERT INTO TIME_SCHEDULE (date_schedule_id, name, start_time, end_time, sequence, created_at)
VALUES (7, '19:00', '19:00:00', '20:30:00', 1, now());

INSERT INTO TIME_SCHEDULE (date_schedule_id, name, start_time, end_time, sequence, created_at)
VALUES (8, '20:30', '20:30:00', '22:00:00', 1, now());

INSERT INTO TIME_SCHEDULE (date_schedule_id, name, start_time, end_time, sequence, created_at)
VALUES (9, '15:00', '15:00:00', '16:30:00', 1, now());

INSERT INTO TIME_SCHEDULE (date_schedule_id, name, start_time, end_time, sequence, created_at)
VALUES (9, '18:00', '18:00:00', '19:30:00', 1, now());

INSERT INTO TIME_SCHEDULE (date_schedule_id, name, start_time, end_time, sequence, created_at)
VALUES (10, '15:00', '15:00:00', '16:30:00', 1, now());

INSERT INTO TIME_SCHEDULE (date_schedule_id, name, start_time, end_time, sequence, created_at)
VALUES (10, '17:30', '17:30:00', '19:00:00', 1, now());

INSERT INTO TIME_SCHEDULE (date_schedule_id, name, start_time, end_time, sequence, created_at)
VALUES (11, '16:00', '16:00:00', '17:30:00', 1, now());

INSERT INTO TIME_SCHEDULE (date_schedule_id, name, start_time, end_time, sequence, created_at)
VALUES (11, '20:00', '20:00:00', '21:30:00', 1, now());
