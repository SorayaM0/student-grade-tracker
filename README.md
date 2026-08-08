# Student Grade Tracker

A simple JavaFX application for managing students and tracking their grades.

The project started as a console-based Java application and was later upgraded with a graphical user interface using JavaFX.

## Features

- Add students with an initial grade
- Add additional grades to existing students
- Automatically calculate each student's average
- Calculate the overall class average
- Display the top-performing student
- Select students directly from the table
- Validate grades between 0 and 100
- Simple and clean JavaFX interface

## Technologies

- Java
- JavaFX
- Maven
- CSS
- Object-Oriented Programming (OOP)

## Project Structure

```text
student-grade-tracker/
├── src/
│   └── main/
│       ├── java/
│       │   ├── Main.java
│       │   ├── Student.java
│       │   └── GradeManager.java
│       └── resources/
│           └── style.css
├── pom.xml
├── .gitignore
└── README.md
```

## How to Run

Make sure Java and Maven are installed.

Clone the repository:

```bash
git clone https://github.com/SorayaM0/student-grade-tracker.git
```

Open the project directory:

```bash
cd student-grade-tracker
```

Run the application:

```bash
mvn clean javafx:run
```

## How It Works

Enter a student's name and their first grade, then click **Add Student**.

To add another grade, select the student from the table, enter the new grade, and click **Add Grade**.

The application automatically updates the student's average, class average, and top student.

## Future Improvements

Possible future additions include:

- Save student data between sessions
- Remove or edit students
- Display individual grade history
- Export grade reports
- Add additional statistics

## Author

Soraya M.