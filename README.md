# Student Grade Tracker

A simple Java console application for managing students and their grades.  
The project demonstrates core Java programming concepts including object-oriented programming, collections, user input, and basic data processing.

## Features

- Add new students
- Add grades for existing students
- View all students and their averages
- Search for a student by name
- Calculate the overall class average
- Display the top-performing student
- Validate grade input between 0 and 100

## Technologies

- Java
- Object-Oriented Programming (OOP)
- ArrayList
- Scanner

## Project Structure

```text
student-grade-tracker/
├── src/
│   ├── Main.java
│   ├── Student.java
│   └── GradeManager.java
├── .gitignore
└── README.md
```

## How to Run

Clone the repository:

```bash
git clone https://github.com/SorayaM0/student-grade-tracker.git
```

Navigate to the project:

```bash
cd student-grade-tracker
```

Compile the application:

```bash
mkdir -p out
javac -d out src/*.java
```

Run the application:

```bash
java -cp out Main
```

## Example Menu

```text
1. Add Student
2. Add Grade
3. View Students
4. Search Student
5. Class Average
6. Top Student
7. Exit
```

## Future Improvements

- Add a JavaFX graphical user interface
- Save student data between sessions
- Add additional grade statistics

## Author

Soraya M.
