PRAGMA foreign_keys = ON;

CREATE TABLE Student (
    student_id INTEGER PRIMARY KEY,
    name TEXT NOT NULL UNIQUE
);

CREATE TABLE ProjectActivity (
    activity_id INTEGER PRIMARY KEY,
    name TEXT NOT NULL UNIQUE
);

CREATE TABLE ActivitySignup (
    student_id INTEGER NOT NULL,
    activity_id INTEGER NOT NULL,
    PRIMARY KEY (student_id, activity_id),
    FOREIGN KEY (student_id)
     REFERENCES Student(student_id),
    FOREIGN KEY (activity_id)
     REFERENCES ProjectActivity(activity_id)
);

CREATE TABLE ProjectGroup (
    group_id INTEGER PRIMARY KEY,
    activity_id INTEGER NOT NULL,
    title TEXT NOT NULL UNIQUE,
    supervisor TEXT NOT NULL,
    UNIQUE (group_id, activity_id),
    FOREIGN KEY (activity_id)
     REFERENCES ProjectActivity(activity_id)
);

CREATE TABLE GroupMembership (
   student_id INTEGER NOT NULL,
   activity_id INTEGER NOT NULL,
   group_id INTEGER NOT NULL,
   PRIMARY KEY (student_id, activity_id),
   FOREIGN KEY (student_id, activity_id)
     REFERENCES ActivitySignup(student_id, activity_id),
   FOREIGN KEY (group_id, activity_id)
     REFERENCES ProjectGroup(group_id, activity_id)
);