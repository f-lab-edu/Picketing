DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `show`;

CREATE TABLE `user`
(
    `id`           BIGINT       NOT NULL AUTO_INCREMENT comment 'ID',
    `email`        VARCHAR(50)  NOT NULL comment '사용자 email',
    `password`     VARCHAR(255) NOT NULL comment '사용자 비밀번호',
    `name`         VARCHAR(50)  NOT NULL comment '사용자 이름',
    `phone_number` VARCHAR(30) comment '사용자 연락처',
    `createdAt`    DATETIME DEFAULT NOW(),
    `modifiedAt`   DATETIME DEFAULT NULL,
    PRIMARY KEY (`id`),
    index          idx_user_email (`email`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4;

create table `show`
(
    `id`           BIGINT       NOT NULL auto_increment comment 'ID',
    `title`        VARCHAR(255) NOT NULL comment '공연 제목',
    `genre`        VARCHAR(255) NOT NULL comment '장르',
    `sub_genre`    VARCHAR(255) comment '세부 장르',
    `start_date`   DATE         NOT NULL comment '공연 시작 일자',
    `end_date`     DATE         NOT NULL comment '공연 종료 일자',
    `venue`        VARCHAR(255) NOT NULL comment '공연 장소',
    `running_time` VARCHAR(50)  NOT NULL comment '공연 시간',
    `intermission` VARCHAR(50)  NOT NULL comment '인터미션',
    `age_group`    VARCHAR(50)  NOT NULL comment '관람 등급',
    `details`      TEXT         NOT NULL comment '공연 세부 내용',
    `is_bookable`  TINYINT      NOT NULL DEFAULT 0 comment '예매 가능 여부',
    `owner_id`     BIGINT       NOT NULL comment '공연 주최자 id',
    PRIMARY KEY (`id`),
    index          idx_show_title (`title`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4;
