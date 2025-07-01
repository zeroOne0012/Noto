-- 개체
CREATE TABLE member (
    member_id      VARCHAR(15)  PRIMARY KEY,
    email          VARCHAR(50)  NOT NULL UNIQUE,
    phone          VARCHAR(15)  NOT NULL UNIQUE,
    nickname       VARCHAR(30),
    password       VARCHAR(255)  NOT NULL,
    role           VARCHAR(20) NOT NULL DEFAULT 'ROLE_USER',
    register_date  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE schedule (
    schedule_idx    INT PRIMARY KEY,
    title           VARCHAR(50) NOT NULL,
    schedule_type   VARCHAR(30) NOT NULL,
    cycle_type      VARCHAR(30),
    active          BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE instance (
    schedule_idx    INT,
    instance_idx    INT,
    achievement     BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (schedule_idx, instance_idx),

    CONSTRAINT fk_instance
    FOREIGN KEY (schedule_idx) REFERENCES Schedule(schedule_idx)
);

CREATE TABLE category (
    member_id       VARCHAR(15),
    category_idx    INT,
    name            VARCHAR(30) NOT NULL,
    color           VARCHAR(10),

    PRIMARY KEY (member_id, category_idx)
);

CREATE TABLE statistic(
    statistic_idx INT PRIMARY KEY,
    complete_count INT NOT NULL DEFAULT 0,
    success_rate REAL DEFAULT 0.0
);