DROP TABLE IF EXISTS `DATE_SCHEDULE`;
DROP TABLE IF EXISTS `TIME_SCHEDULE`;

CREATE TABLE `DATE_SCHEDULE`
(
    `id`          BIGINT      NOT NULL AUTO_INCREMENT comment 'ID',
    `show_id`     BIGINT      NOT NULL,
    `name`        VARCHAR(50) NOT NULL comment '날짜 명칭',
    `start_date`  DATE        NOT NULL comment '시작 일자',
    `end_date`    DATE        NOT NULL comment '종료 일자',
    `created_at`  DATETIME DEFAULT NOW(),
    `modified_at` DATETIME DEFAULT NULL,
    PRIMARY KEY (`id`),
    index         idx_start_date (`start_date`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4;

create table `TIME_SCHEDULE`
(
    `id`               BIGINT      NOT NULL AUTO_INCREMENT comment 'ID',
    `date_schedule_id` BIGINT      NOT NULL,
    `name`             VARCHAR(50) NOT NULL comment '시각 명칭',
    `start_time`       TIME        NOT NULL comment '시작 시각',
    `end_time`         TIME        NOT NULL comment '종료 시각',
    `sequence`         INT         NOT NULL DEFAULT 1 comment '공연 회차',
    `created_at`       DATETIME             DEFAULT NOW(),
    `modified_at`      DATETIME             DEFAULT NULL,
    PRIMARY KEY (`id`),
    index              idx_start_time (`start_time`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `DATE_SCHEDULE`
    ADD FOREIGN KEY (show_id) references `SHOW` (id);

ALTER TABLE `TIME_SCHEDULE`
    ADD FOREIGN KEY (date_schedule_id) references `DATE_SCHEDULE` (id);

