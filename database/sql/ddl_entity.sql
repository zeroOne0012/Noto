// 개체
CREATE TABLE member (
    member_id      VARCHAR(15)  PRIMARY KEY,
    email          VARCHAR(50)  NOT NULL UNIQUE,
    phone          VARCHAR(15)  NOT NULL UNIQUE,
    nickname       VARCHAR(30),
    password       VARCHAR(255)  NOT NULL,
    register_date  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Schedule (
    schedule_idx    INT PRIMARY KEY,
    title           VARCHAR(50) NOT NULL,
    schedule_type   VARCHAR(30) NOT NULL,
    cycle_type      VARCHAR(30),
    active          BOOLEAN NOT NULL DEFAULT TRUE,
);

CREATE TABLE NonRoutineSchedule (
    schedule_idx    INT PRIMARY KEY,
    title           VARCHAR(50) NOT NULL,
    schedule_type   VARCHAR(30) NOT NULL,
    start_date      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    end_date        TIMESTAMP,

    CONSTRAINT fk_none_routine_schedule
    FOREIGN KEY (schedule_idx) REFERENCES Schedule(schedule_idx)
);

CREATE TABLE Instance (
    schedule_idx    INT,
    instance_idx    INT,
    achievement     BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (schedule_idx, instance_idx),

    CONSTRAINT fk_instance
    FOREIGN KEY (schedule_idx) REFERENCES Schedule(schedule_idx)
);

CREATE TABLE Category (
    member_id       VARCHAR(15),
    category_idx    INT,
    name            VARCHAR(30) NOT NULL,
    color           VARCHAR(10),

    PRIMARY KEY (member_id, category_idx)
);

CREATE TABLE Statistic(
    statistic_idx INT PRIMARY KEY,
    complete_count INT NOT NULL DEFAULT 0,
    success_rate REAL DEFAULT 0.0
)