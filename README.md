# Portfolio Project 2 - Software Development

## Project Description

A JavaFX desktop application where students sign up for project activities and create or join project groups. Data is stored in SQLite through JDBC. This is an independent implementation for the second of three Software Development portfolios at Roskilde University.

## Implemented Features

- Load students and project activities from the database into combo boxes.
- Sign up a student for an activity, with feedback for existing signups.
- Create a project group with a title, supervisor and the selected student as its first member.
- List existing groups for the selected activity and join a selected group.
- Query a student's group for a specific activity, returning zero or one row.

The interface is constructed in Java without FXML. Generated FXML/controller files may remain from the IntelliJ template, but the application interface does not load them.

## Domain Rules

- Students may sign up for multiple activities.
- A student may belong to at most one group per activity.
- Group members must be signed up for the group's activity.
- Each group belongs to one activity and has a unique project title and a supervisor.
- Creating a group also creates its initial membership in the same transaction.
- Student names and activity names are unique in this simplified assignment model.

## Database and SQL Files

- [Tables.sql](Tables.sql): creates the five tables and their constraints.
- [Sample.sql](Sample.sql): inserts the fixed example dataset.
- [Queries.sql](Queries.sql): joins membership and group data for a selected student and activity.

The tables are Student, ProjectActivity, ActivitySignup, ProjectGroup and GroupMembership. ActivitySignup and GroupMembership use the composite primary key (student_id, activity_id). Composite foreign keys enforce signup and matching group/activity references. ProjectGroup declares UNIQUE(group_id, activity_id) to support the membership reference.

To recreate the database, use an empty SQLite file and run Tables.sql, then the complete Sample.sql script on the same connection. Enable foreign keys before starting the sample-data transaction. Do not rerun these creation/insertion scripts against an already populated database. Stop and roll back on an insertion error.

## Code Structure

The Maven application is in [application](application/).

- Launcher starts JavaFX; HelloApplication builds the interface and handles user actions.
- Database opens connections and enables PRAGMA foreign_keys = ON on each connection.
- Student, ProjectActivity and ProjectGroup represent domain data.
- StudentRepo and ProjectActivityRepo read selection data.
- ActivitySignupRepo saves signups.
- ProjectGroupRepo creates groups, retrieves groups by activity and saves memberships.
- DatabaseTest is a console connection/query check.

SQL is kept in repository classes, separate from the JavaFX interface. Parameterized writes use PreparedStatement, and try-with-resources closes JDBC resources. Group creation commits the group and first membership together, or rolls both back on failure.

## Technologies

- JDK 26
- JavaFX 26.0.2
- SQLite and Xerial SQLite JDBC 3.53.4.0
- Maven / Maven Wrapper

## Running and Repository Setup

Open application/pom.xml as a Maven project in IntelliJ and select JDK 26. Synchronize Maven to download the JavaFX dependencies.

The GitHub checkout and the separately prepared submission ZIP are not yet identical: the submission copy has a relative database path and a Maven SQLite JDBC dependency. These packaging changes must not be assumed to exist in this checkout.

For the current checkout:

1. Ensure the SQLite JDBC driver is available to the application module. If it is not declared in pom.xml, manually add sqlite-jdbc-3.53.4.0.jar through Project Structure > Modules > Dependencies, following the course method.
2. Create a database using Tables.sql and Sample.sql, or use your existing database.
3. Check DATABASE_FILE in Database.java and point it to that database. A personal absolute path must be adjusted on another computer. If using Path.of("verification.sqlite"), place the file in the application working directory.
4. Run ruc.portfolio2.application.Launcher with application as the working directory and these VM options:

    --enable-native-access=javafx.graphics,org.xerial.sqlitejdbc

Database files are not tracked in this repository. The submission ZIP includes its database and RUNNING.md setup instructions. The manual JAR file alone is not sufficient unless it is added to the project's dependencies.

## Verification Performed

During development, the application was exercised for new and repeated signups, group creation, joining with and without activity signup, and rejection of additional memberships. A failed group-creation transaction was checked for an unwanted leftover group. SQL checks covered example row counts, foreign-key integrity, and both populated and empty group-query results. These are manual development checks, not a claim of an automated test suite or CI build.

## Submission

The assignment is submitted as a PDF with short implementation descriptions, an E/R diagram, screenshots of program runs and member email addresses, plus a ZIP containing source code and SQL scripts. The PDF and packaged ZIP are separate deliverables; GitHub does not replace the required submission.

## Assignment Source

Mads Rosendahl  
Software Development / RUC / Datalogi - Autumn 2026
