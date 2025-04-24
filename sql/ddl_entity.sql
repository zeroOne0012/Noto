// 개체
CREATE TABLE member (
    member_id      VARCHAR(15)  PRIMARY KEY,
    email          VARCHAR(20)  NOT NULL UNIQUE,
    phone          VARCHAR(15)  NOT NULL UNIQUE,
    nickname       VARCHAR(30),
    password       VARCHAR(15)  NOT NULL CHECK (char_length(password) BETWEEN 8 AND 15),
    is_verified    BOOLEAN      DEFAULT FALSE,
    register_date  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Schedule (
    schedule_idx    INT PRIMARY KEY,
    title           VARCHAR(50) NOT NULL,
    schedule_type   VARCHAR(30) NOT NULL
);

CREATE TABLE RoutineSchedule (
    schedule_idx    INT PRIMARY KEY,
    title           VARCHAR(50) NOT NULL,
    schedule_type   VARCHAR(30) NOT NULL,
    cycle_type      VARCHAR(30),
    active          BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT fk_routine_schedule
    FOREIGN KEY (schedule_idx) REFERENCES Schedule(schedule_idx)
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

CREATE TABLE Task (
    schedule_idx    INT,
    task_idx        INT,
    achievement     BOOLEAN NOT NULL,
    content         VARCHAR(500),
    priority        INT NOT NULL DEFAULT 1,

    PRIMARY KEY (schedule_idx, task_idx),

    CONSTRAINT fk_task
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