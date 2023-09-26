DROP TABLE IF EXISTS `USERS`;
DROP TABLE IF EXISTS `SHOW`;
DROP TABLE IF EXISTS `SEAT`;
DROP TABLE IF EXISTS `SEAT_GRADE`;

CREATE TABLE `USERS`
(
    `id`           BIGINT       NOT NULL AUTO_INCREMENT comment 'ID',
    `email`        VARCHAR(50)  NOT NULL comment '사용자 email',
    `password`     VARCHAR(255) NOT NULL comment '사용자 비밀번호',
    `name`         VARCHAR(50)  NOT NULL comment '사용자 이름',
    `phone_number` VARCHAR(30) comment '사용자 연락처',
    `created_at`   DATETIME DEFAULT NOW(),
    `modified_at`  DATETIME DEFAULT NULL,
    PRIMARY KEY (`id`),
    index idx_user_email (`email`)
) engine = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `SHOW`
(
    `id`           BIGINT       NOT NULL auto_increment comment 'ID',
    `title`        VARCHAR(255) NOT NULL comment '공연 제목',
    `genre`        VARCHAR(255) NOT NULL comment '장르',
    `sub_genre`    VARCHAR(255) comment '세부 장르',
    `start_date`   DATE         NOT NULL comment '공연 시작 일자',
    `end_date`     DATE         NOT NULL comment '공연 종료 일자',
    `venue`        VARCHAR(255) NOT NULL comment '공연 장소',
    `running_time` INT          NOT NULL DEFAULT 0 comment '공연 시간',
    `intermission` INT          NOT NULL DEFAULT 0 comment '인터미션',
    `age_group`    VARCHAR(50)  NOT NULL comment '관람 등급',
    `details`      TEXT         NOT NULL comment '공연 세부 내용',
    `is_bookable`  TINYINT      NOT NULL DEFAULT 0 comment '예매 가능 여부',
    `owner_id`     BIGINT       NOT NULL comment '공연 주최자 id',
    `created_at`   DATETIME              DEFAULT NOW(),
    `modified_at`  DATETIME              DEFAULT NULL,
    PRIMARY KEY (`id`),
    index idx_show_title (`title`)
) engine = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `SEAT`
(
    `id`               BIGINT NOT NULL auto_increment comment 'ID',
    `show_id`          BIGINT NOT NULL comment '공연 id',
    `time_schedule_id` BIGINT   DEFAULT 1 comment '시간 스케줄 id',
    `created_at`       DATETIME DEFAULT NOW(),
    `modified_at`      DATETIME DEFAULT NULL,
    PRIMARY KEY (`id`)
) engine = InnoDB
  DEFAULT CHARSET = utf8mb4;


CREATE TABLE `SEAT_GRADE`
(
    `id`          BIGINT      NOT NULL auto_increment comment 'ID',
    `title`       VARCHAR(50) NOT NULL comment '좌석명',
    `price`       DECIMAL     NOT NULL comment '가격',
    `seat_id`     BIGINT      NOT NULL comment '좌석 id',
    `created_at`  DATETIME DEFAULT NOW(),
    `modified_at` DATETIME DEFAULT NULL,
    PRIMARY KEY (`id`)
) engine = InnoDB
  DEFAULT CHARSET = utf8mb4;

ALTER TABLE `SEAT`
    ADD FOREIGN KEY (show_id) references `SHOW` (id);

ALTER TABLE `SEAT_GRADE`
    ADD FOREIGN KEY (seat_id) references `SEAT` (id);
