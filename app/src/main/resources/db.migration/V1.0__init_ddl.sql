DROP TABLE IF EXISTS `SHOWS`;
DROP TABLE IF EXISTS `SCHEDULE`;
DROP TABLE IF EXISTS `DATE_SCHEDULE`;
DROP TABLE IF EXISTS `TIME_SCHEDULE`;
DROP TABLE IF EXISTS `USERS`;
DROP TABLE IF EXISTS `THEATER_SEAT_GRADE`;
DROP TABLE IF EXISTS `TIME_SCHEDULE_SEAT_GRADE`;
DROP TABLE IF EXISTS `RESERVATION`;
DROP TABLE IF EXISTS `RESERVED_SEAT`;

CREATE TABLE `SHOWS`
(
    `id`           BIGINT       NOT NULL,
    `title`        VARCHAR(255) NOT NULL COMMENT '공연 제목',
    `genre`        VARCHAR(255) NOT NULL COMMENT '공연 장르',
    `sub_genre`    VARCHAR(255) NULL COMMENT '세부 장르',
    `start_date`   DATE         NOT NULL COMMENT '공연 시작 일자',
    `end_date`     DATE         NOT NULL COMMENT '공연 종료 일자',
    `venue`        VARCHAR(255) NOT NULL COMMENT '공연 장소',
    `running_time` INT          NOT NULL DEFAULT 0 COMMENT '공연 시간',
    `intermission` INT          NOT NULL DEFAULT 0 COMMENT '인터미션',
    `age_group`    VARCHAR(50)  NOT NULL COMMENT '공연 관람 등급',
    `details`      TEXT         NOT NULL COMMENT '공연 세부 내용',
    `is_bookable`  TINYINT      NOT NULL DEFAULT 0 COMMENT '공연 예매 가능 여부',
    `owner_id`     BIGINT       NOT NULL COMMENT '공연 주최자 id',
    `created_at`   DATETIME     NOT NULL DEFAULT NOW(),
    `modified_at`  DATETIME     NULL     DEFAULT NULL,
    index idx_show_title (`title`)
) engine = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `SCHEDULE`
(
    `id`          BIGINT       NOT NULL,
    `show_id`     BIGINT       NOT NULL,
    `created_at`  VARCHAR(255) NOT NULL DEFAULT NOW(),
    `modified_at` DATETIME     NULL     DEFAULT NULL
) engine = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `DATE_SCHEDULE`
(
    `id`          BIGINT   NOT NULL,
    `schedule_id` BIGINT   NOT NULL,
    `show_date`   DATE     NOT NULL COMMENT '공연 일자',
    `created_at`  DATETIME NOT NULL DEFAULT NOW(),
    `modified_at` DATETIME NULL     DEFAULT NULL
) engine = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `TIME_SCHEDULE`
(
    `id`               BIGINT   NOT NULL,
    `date_schedule_id` BIGINT   NOT NULL,
    `start_time`       DATETIME NOT NULL COMMENT '공연 시작 시각',
    `created_at`       DATETIME NOT NULL DEFAULT NOW(),
    `modified_at`      DATETIME NULL     DEFAULT NULL
) engine = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `USERS`
(
    `id`           BIGINT       NOT NULL,
    `email`        VARCHAR(255) NOT NULL COMMENT '사용자 이메일 주소',
    `password`     VARCHAR(255) NOT NULL COMMENT '사용자 계정 비밀번호',
    `name`         VARCHAR(50)  NOT NULL COMMENT '사용자 이름',
    `phone_number` VARCHAR(30)  NULL COMMENT '사용자 연락처',
    `created_at`   DATETIME     NOT NULL DEFAULT NOW(),
    `modified_at`  DATETIME     NULL     DEFAULT NULL,
    index idx_user_email (`email`)
) engine = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `THEATER_SEAT_GRADE`
(
    `id`          BIGINT       NOT NULL,
    `schedule_id` BIGINT       NOT NULL,
    `seat_grade`  VARCHAR(255) NOT NULL COMMENT '공연장의 좌석 등급',
    `created_at`  DATETIME     NOT NULL DEFAULT NOW(),
    `modified_at` DATETIME     NULL     DEFAULT NULL
) engine = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `TIME_SCHEDULE_SEAT_GRADE`
(
    `id`               BIGINT       NOT NULL,
    `time_schedule_id` BIGINT       NOT NULL,
    `seat_grade`       VARCHAR(255) NOT NULL COMMENT '특정 시간대의 좌석 등급',
    `created_at`       DATETIME     NOT NULL DEFAULT NOW(),
    `modified_at`      DATETIME     NULL     DEFAULT NULL
) engine = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `RESERVATION`
(
    `id`          BIGINT       NOT NULL,
    `user_id`     BIGINT       NOT NULL,
    `show_id`     BIGINT       NOT NULL,
    `Field3`      VARCHAR(255) NULL,
    `created_at`  DATETIME     NOT NULL DEFAULT NOW(),
    `modified_at` DATETIME     NOT NULL DEFAULT NULL
) engine = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `RESERVED_SEAT`
(
    `id`                          BIGINT   NOT NULL,
    `reservation_id`              BIGINT   NOT NULL,
    `time_schedule_seat_grade_id` BIGINT   NOT NULL,
    `created_at`                  DATETIME NOT NULL DEFAULT NOW(),
    `modified_at`                 DATETIME NULL     DEFAULT NULL
) engine = InnoDB
  DEFAULT CHARSET = utf8mb4;

ALTER TABLE `SHOWS`
    ADD CONSTRAINT `PK_SHOW` PRIMARY KEY (
                                          `id`
        );

ALTER TABLE `SCHEDULE`
    ADD CONSTRAINT `PK_SCHEDULE` PRIMARY KEY (
                                              `id`
        );

ALTER TABLE `DATE_SCHEDULE`
    ADD CONSTRAINT `PK_DATE_SCHEDULE` PRIMARY KEY (
                                                   `id`
        );

ALTER TABLE `TIME_SCHEDULE`
    ADD CONSTRAINT `PK_TIME_SCHEDULE` PRIMARY KEY (
                                                   `id`
        );

ALTER TABLE `USERS`
    ADD CONSTRAINT `PK_USER` PRIMARY KEY (
                                          `id`
        );

ALTER TABLE `THEATER_SEAT_GRADE`
    ADD CONSTRAINT `PK_THEATER_SEAT_GRADE` PRIMARY KEY (
                                                        `id`
        );

ALTER TABLE `TIME_SCHEDULE_SEAT_GRADE`
    ADD CONSTRAINT `PK_TIME_SCHEDULE_SEAT_GRADE` PRIMARY KEY (
                                                              `id`
        );

ALTER TABLE `RESERVATION`
    ADD CONSTRAINT `PK_RESERVATION` PRIMARY KEY (
                                                 `id`
        );

ALTER TABLE `RESERVED_SEAT`
    ADD CONSTRAINT `PK_RESERVED_SEAT` PRIMARY KEY (
                                                   `id`
        );

ALTER TABLE `SCHEDULE`
    ADD CONSTRAINT `FK_SHOW_TO_SCHEDULE_1` FOREIGN KEY (
                                                        `show_id`
        )
        REFERENCES `SHOWS` (
                            `id`
            );

ALTER TABLE `DATE_SCHEDULE`
    ADD CONSTRAINT `FK_SCHEDULE_TO_DATE_SCHEDULE_1` FOREIGN KEY (
                                                                 `schedule_id`
        )
        REFERENCES `SCHEDULE` (
                               `id`
            );

ALTER TABLE `TIME_SCHEDULE`
    ADD CONSTRAINT `FK_DATE_SCHEDULE_TO_TIME_SCHEDULE_1` FOREIGN KEY (
                                                                      `date_schedule_id`
        )
        REFERENCES `DATE_SCHEDULE` (
                                    `id`
            );

ALTER TABLE `THEATER_SEAT_GRADE`
    ADD CONSTRAINT `FK_SCHEDULE_TO_THEATER_SEAT_GRADE_1` FOREIGN KEY (
                                                                      `schedule_id`
        )
        REFERENCES `SCHEDULE` (
                               `id`
            );

ALTER TABLE `TIME_SCHEDULE_SEAT_GRADE`
    ADD CONSTRAINT `FK_TIME_SCHEDULE_TO_TIME_SCHEDULE_SEAT_GRADE_1` FOREIGN KEY (
                                                                                 `time_schedule_id`
        )
        REFERENCES `TIME_SCHEDULE` (
                                    `id`
            );

ALTER TABLE `RESERVATION`
    ADD CONSTRAINT `FK_USER_TO_RESERVATION_1` FOREIGN KEY (
                                                           `user_id`
        )
        REFERENCES `USERS` (
                            `id`
            );

ALTER TABLE `RESERVATION`
    ADD CONSTRAINT `FK_SHOW_TO_RESERVATION_1` FOREIGN KEY (
                                                           `show_id`
        )
        REFERENCES `SHOWS` (
                            `id`
            );

ALTER TABLE `RESERVED_SEAT`
    ADD CONSTRAINT `FK_RESERVATION_TO_RESERVED_SEAT_1` FOREIGN KEY (
                                                                    `reservation_id`
        )
        REFERENCES `RESERVATION` (
                                  `id`
            );

ALTER TABLE `RESERVED_SEAT`
    ADD CONSTRAINT `FK_TIME_SCHEDULE_SEAT_GRADE_TO_RESERVED_SEAT_1` FOREIGN KEY (
                                                                                 `time_schedule_seat_grade_id`
        )
        REFERENCES `TIME_SCHEDULE_SEAT_GRADE` (
                                               `id`
            );
