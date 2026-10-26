SELECT project_group.group_id,
       project_group.title,
       project_group.supervisor
FROM GroupMembership AS membership
JOIN ProjectGroup AS project_group
    ON membership.group_id = project_group.group_id
    AND membership.activity_id = project_group.activity_id
WHERE membership.student_id = 1
    AND membership.activity_id = 10;

