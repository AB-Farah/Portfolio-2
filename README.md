# Portfolio Project 2 - Software Development

## Project Description

This portfolio is about graphical user interfaces and databases, focusing on JavaFX, relational database modelling, SQL and JDBC.

It is the second of three parts to be submitted for the exam as a mini-project. This repository is a new, independent implementation without code reused from Portfolio 1.

## Assignment Description

Develop an application where students can sign up for project activities in a bachelor's programme and create or join project groups.

The assignment consists of the following parts:

1. Construct a JavaFX interface without FXML for activity signups and creating or joining project groups.
2. Model the domain with an E/R diagram showing cardinalities, primary keys, foreign keys and key types.
3. Create database tables and insert sample data, enabling foreign key checks with `PRAGMA foreign_keys = ON;`.
4. Create a query that finds the project group a student has joined for a specific project activity, returning zero or one result.
5. Implement a JDBC backend that fetches project activities from the database and stores project group information, keeping GUI operations separate from database interaction.

## Project Activities and Groups

Students sign up for a project activity before creating or joining a group for that activity.

- A student can sign up for multiple project activities.
- A student can belong to at most one group per project activity.
- Every group member must be signed up for the group's activity.
- Each group belongs to one project activity and has a project title and a supervisor.
- The student who creates a group becomes its initial member.

The assignment assumes that student names, study activity names and project titles are distinct.

## Planned Database Model

- `Student`: student identifiers and names.
- `ProjectActivity`: project activity identifiers and names.
- `ActivitySignup`: student signups for project activities.
- `ProjectGroup`: group identifiers, project titles, supervisors and associated activities.
- `GroupMembership`: student group memberships for individual activities.

`ActivitySignup` and `GroupMembership` use `(student_id, activity_id)` as a composite primary key. The planned foreign keys ensure that group members are signed up for the activity and that their group belongs to the same activity.

## Technologies

- Java
- JavaFX without FXML
- SQLite
- JDBC

## Project Status

The project is in the design stage. The E/R diagram has been drafted; the application and database implementation are the next steps.

## Submission

The submission consists of a PDF with short explanations, an E/R diagram and screenshots of the running application, together with a ZIP file containing the source code. SQL table creation commands and the required query must be included in either the PDF or the ZIP file. The PDF should include group members' email addresses.

## Assignment Source

Mads Rosendahl  
Software Development / RUC / Datalogi - Autumn 2026
