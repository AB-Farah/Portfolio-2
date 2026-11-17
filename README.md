# Portfolio Project 2 - Software Development

## Project Description

This portfolio is about graphical user interfaces and databases.

It is the second of three parts that should be submitted for the exam as a mini-project.

## Assignment Description

Implement an application for students to sign up for project activities in a bachelor's programme.

The assignment consists of the following parts:

1. Construct a JavaFX program where students can select project activities and form or join project groups.
2. Model the domain with an E/R diagram.
3. Create tables and insert some sample data into the tables.
4. Create a query that lists the project group a student has joined for a project activity.
5. Create a backend (model) for the GUI program so that project activities are fetched from the database and project groups are stored in the database.

## Domain Rules

- Students sign up for project activities before forming project groups.
- A project group has a title, a supervisor and belongs to one project activity.
- A student can be in at most one project group for each project activity.
- All students in a group must be signed up for that activity.
- Student names, activity names and project titles are unique.

## Features

- Select a student and a project activity, and sign up.
- Create a new project group with the selected student as its first member.
- Choose an existing group for the selected activity and join it.
- Feedback messages for successful and rejected actions.

The interface is built in Java without FXML.

## Code Structure

The program follows the Model-View-Controller pattern from the lectures:

- `Launcher` - starts the program.
- `View` - creates the window and its components, and shows what it is told.
- `Controller` - handles user actions and connects the view and the model.
- `Model` - all SQL for reading and saving students, activities, signups and groups.
- `MyDB` - the database connection class from the course Moodle page.

Foreign key checks are enabled with `PRAGMA foreign_keys = ON;` when the model is created.

## Database

The database has five tables: `Student`, `ProjectActivity`, `ActivitySignup`, `ProjectGroup` and `GroupMembership`.

- `Tables.sql` - creates the tables and constraints.
- `Sample.sql` - inserts sample data.
- `Queries.sql` - the query that finds a student's group for a project activity.

## How to Run

1. Open the `application` folder in IntelliJ as a Maven project, and let Maven download JavaFX and the SQLite JDBC driver.
2. Create the database file `identifier.sqlite` in the `application` folder by running `Tables.sql` and then `Sample.sql`.
3. Run `Launcher`.

## Technologies

- Java (JDK 26)
- JavaFX 26.0.2
- SQLite with the Xerial SQLite JDBC driver 3.53.4.0
- Maven

## Assignment Source

Mads Rosendahl
Software Development / RUC / Datalogi - Autumn 2026
