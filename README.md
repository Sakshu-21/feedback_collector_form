# Feedback Collector Form

A JavaFX-based desktop application that allows students to submit course 
feedback through a clean and interactive form. All feedback is stored 
directly into a MySQL database.

##  Features
- Student details input (Name, Email, Roll No, Department, Semester)
- Course & Faculty information fields
- Star rating system (1–5) using radio buttons
- Comments and suggestions text area
- Direct MySQL database integration
- Success/Error popup alerts

## Tech Stack
- Java (JDK 8+)
- JavaFX (UI Framework)
- MySQL (Database)
- JDBC (Database Connectivity)

## Setup & How to Run

### 1. Clone the repository
git clone https://github.com/Sakshu-21/feedback-collector-form.git

### 2. Setup the MySQL Database
CREATE DATABASE feedbackdb;
USE feedbackdb;
CREATE TABLE feedback (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    roll VARCHAR(50),
    dept VARCHAR(50),
    semester VARCHAR(20),
    course VARCHAR(100),
    course_code VARCHAR(50),
    faculty VARCHAR(100),
    rating VARCHAR(5),
    comments TEXT,
    suggestion TEXT
);

### 3. Update Database Credentials
In `FeedbackForm.java` and `DatabaseTest.java`, update:
- URL: jdbc:mysql://localhost:3306/feedbackdb
- USER: your_mysql_username
- PASS: your_mysql_password

### 4. Run the project
Open in VS Code, make sure JavaFX and MySQL connector 
are in your `/lib` folder, then run Main.java

## Project Structure
- Main.java          → Entry point
- FeedbackForm.java  → UI form + database logic
- DatabaseTest.java  → Tests database connection
- lib/               → JavaFX & MySQL JDBC libraries
## Author
**Sakshi**
[GitHub](https://github.com/Sakshu-21)