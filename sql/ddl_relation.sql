// 관계
CREATE TABLE memo (
    member_id     VARCHAR(15),
    schedule_idx  INT,
    content       VARCHAR(500) NOT NULL,

    PRIMARY KEY (member_id, schedule_idx),

    FOREIGN KEY (member_id) REFERENCES Member(member_id),
    FOREIGN KEY (schedule_idx) REFERENCES Schedule(schedule_idx)
);

CREATE TABLE share (
    member_id     VARCHAR(15),
    schedule_idx  INT,

    PRIMARY KEY (member_id, schedule_idx),

    FOREIGN KEY (member_id) REFERENCES Member(member_id),
    FOREIGN KEY (schedule_idx) REFERENCES Schedule(schedule_idx)
);

CREATE TABLE create_category (
    member_id     VARCHAR(15),
    category_idx  INT,

    PRIMARY KEY (member_id, category_idx),

    FOREIGN KEY (member_id) REFERENCES Member(member_id),
    FOREIGN KEY (category_idx) REFERENCES Category(category_idx)
);

CREATE TABLE create_instance (
    schedule_idx   INT,
    instance_idx   INT,
    date           TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (schedule_idx, instance_idx),

    FOREIGN KEY (schedule_idx) REFERENCES Schedule(schedule_idx),
    FOREIGN KEY (instance_idx) REFERENCES Instance(instance_idx)
);

CREATE TABLE devide_task (
    schedule_idx   INT,
    task_idx       INT,
    date           TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (schedule_idx, task_idx),

    FOREIGN KEY (schedule_idx) REFERENCES Schedule(schedule_idx),
    FOREIGN KEY (task_idx) REFERENCES Task(task_idx)
);

CREATE TABLE include_instance (
    instance_idx   INT,
    category_idx   INT,

    PRIMARY KEY (instance_idx, category_idx),

    FOREIGN KEY (instance_idx) REFERENCES Instance(instance_idx),
    FOREIGN KEY (category_idx) REFERENCES Category(category_idx)
);

CREATE TABLE include_task (
    task_idx       INT,
    category_idx   INT,

    PRIMARY KEY (task_idx, category_idx),

    FOREIGN KEY (task_idx) REFERENCES Task(task_idx),
    FOREIGN KEY (category_idx) REFERENCES Category(category_idx)
);

CREATE TABLE include_task (
    task_idx       INT,
    category_idx   INT,

    PRIMARY KEY (task_idx, category_idx),

    FOREIGN KEY (task_idx) REFERENCES Task(task_idx),
    FOREIGN KEY (category_idx) REFERENCES Category(category_idx)
);

CREATE TABLE analize (
    schedule_idx    INT,
    statistic_idx   INT,

    PRIMARY KEY (schedule_idx, statistic_idx),

    FOREIGN KEY (schedule_idx) REFERENCES Schedule(schedule_idx),
    FOREIGN KEY (statistic_idx) REFERENCES Statistic(statistic_idx)
);
