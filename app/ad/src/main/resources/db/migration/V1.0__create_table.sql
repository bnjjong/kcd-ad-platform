/*
 * Copyright (c) 2024
 * Written by JongSang Han<dogfootmaster@gmail.com>
 * Last modified on 2024/6/9
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

CREATE TABLE user
(
    `id`              varchar(255)      NOT NULL    COMMENT '아이디. UUID',
    `age`             int               NOT NULL    COMMENT '나이',
    `name`            varchar(50)       NOT NULL    COMMENT '이름',
    `gender`          enum ('MAN', 'WOMAN')  NOT NULL    COMMENT '성별',
    `korea_region`    enum ('BUSAN', 'CHUNGCHEONGBUK', 'CHUNGCHEONGNAM', 'DAEGU', 'DAEJEON', 'GANGWON', 'GWANGJU', 'GYEONGGI', 'GYEONGSANGBUK', 'GYEONGSANGNAM', 'INCHEON', 'JEJU', 'JEOLLABUK', 'JEOLLANAM', 'SEJONG', 'SEOUL', 'ULSAN') NOT NULL    COMMENT '지역',
    `classification`  varchar(100)      NOT NULL    COMMENT '업종명',
    `monthly_sales`   DECIMAL(14, 2)    NOT NULL    COMMENT '월 매출',
    check (`age` >= 0),
    check (`monthly_sales` >= 0),
    PRIMARY KEY (id)
);


CREATE TABLE campaign
(
    `id`            varchar(50)     NOT NULL    COMMENT '아이디',
    `placement`     varchar(100)    NOT NULL    COMMENT '지면명',
    `product_type`  varchar(100)    NOT NULL    COMMENT '지면 타입',
    PRIMARY KEY (id)
);

-- 테이블 Comment 설정 SQL - campaign
ALTER TABLE campaign COMMENT '캠페인';

-- Unique Index 설정 SQL - campaign(placement)
CREATE UNIQUE INDEX `uix-campaign-placement`
    ON campaign(placement);


CREATE TABLE ad_group
(
    `id`           bigint           NOT NULL    AUTO_INCREMENT COMMENT '아이디',
    `campaign_id`  varchar(255)     NOT NULL    COMMENT '캠페인 아이디',
    `publish_yn`   enum ('N', 'Y')  NOT NULL    DEFAULT 'Y' COMMENT '개제여부',
    `start_date`   date             NULL        COMMENT '노출시작일',
    `end_date`     date             NULL        COMMENT '노출종료일',
    `priority`     decimal(4, 1)    NOT NULL    COMMENT '우선순위. 오름차순',
    PRIMARY KEY (id)
);

-- 테이블 Comment 설정 SQL - ad_group
ALTER TABLE ad_group COMMENT '광고 그룹';

-- Foreign Key 설정 SQL - ad_group(campaign_id) -> campaign(id)
ALTER TABLE ad_group
    ADD CONSTRAINT `fk-ad_group-campaign-n1` FOREIGN KEY (campaign_id)
        REFERENCES campaign (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


CREATE TABLE creative
(
    `id`                bigint           NOT NULL    AUTO_INCREMENT COMMENT '아이디',
    `ad_group_id`       bigint           NOT NULL    COMMENT '애드 그룹 아이디',
    `title`             varchar(100)     NOT NULL    COMMENT '소재 타이틀',
    `description`       varchar(1000)    NULL        COMMENT '소재 설명',
    `text_color`        varchar(30)      NULL        COMMENT 'CSS 텍스트 컬러',
    `background_color`  varchar(30)      NULL        COMMENT 'CSS 백그라운드 컬러',
    `background_image`  varchar(255)     NOT NULL    COMMENT 'CSS 백그라운드 이미지  URL',
    `landing_url`       varchar(255)     NOT NULL    COMMENT '랜딩 URL',
    `view_count`        BIGINT           NOT NULL    DEFAULT 0 COMMENT '소재 노출수',
    `limit_exposure`    BIGINT           NOT NULL    DEFAULT 0 COMMENT '소재 노출 제한(카운트). view_count >= limit_exposure 되면 노출이 제한됨',
    PRIMARY KEY (id)
);

ALTER TABLE creative
    ADD CONSTRAINT `fk-creative-ad_group-n1` FOREIGN KEY (ad_group_id)
        REFERENCES ad_group (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


CREATE TABLE audience_condition
(
    `id`            bigint          NOT NULL    AUTO_INCREMENT COMMENT '아이디',
    `ad_group_id`   bigint          NOT NULL    COMMENT '애드 그룹 아이디',
    `user_column`   varchar(50)     NOT NULL    COMMENT '오디언스 필터 컬럼',
    `operator`      varchar(50)     NOT NULL    COMMENT '오디언스 필터 연산자',
    `column_value`  varchar(255)    NOT NULL    COMMENT '오디언스 필터 값',
    PRIMARY KEY (id)
);

ALTER TABLE audience_condition
    ADD CONSTRAINT `fk-audience_condition-ad_group-n1` FOREIGN KEY (ad_group_id)
        REFERENCES ad_group (id) ON DELETE RESTRICT ON UPDATE RESTRICT;




