PRAGMA foreign_keys = ON;

BEGIN TRANSACTION;

INSERT INTO Student (student_id, name)
VALUES
    (1, 'Adam'),
    (2, 'Helen'),
    (3, 'William');

INSERT INTO ProjectActivity (activity_id, name)
VALUES
    (10, 'Database-project'),
    (20, 'Java-project');

INSERT INTO ActivitySignup (student_id, activity_id)
VALUES
    (1, 10),
    (2, 10),
    (3, 10),
    (3, 20);

INSERT INTO ProjectGroup
(group_id, activity_id, title, supervisor)
VALUES
    (100, 10, 'Art Gallery Database', 'Bob'),
    (101, 10, 'Library Database', 'Ross');


INSERT INTO GroupMembership
(student_id, activity_id, group_id)
VALUES
    (1, 10, 100),
    (2, 10, 101),
    (3, 10, 100);

COMMIT;