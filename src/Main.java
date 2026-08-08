import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GradeManager manager = new GradeManager();

        boolean running = true;

        while (running) {

            System.out.println("\n========================");
            System.out.println("  Student Grade Tracker");
            System.out.println("========================");

            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. View Students");
            System.out.println("4. Search Student");
            System.out.println("5. Class Average");
            System.out.println("6. Top Student");
            System.out.println("7. Exit");

            System.out.print("\nChoose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();

                    manager.addStudent(name);

                    System.out.println("Student added successfully.");
                    break;

                case "2":
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();

                    Student student = manager.findStudent(studentName);

                    if (student == null) {
                        System.out.println("Student not found.");
                        break;
                    }

                    System.out.print("Enter grade: ");

                    try {
                        double grade = Double.parseDouble(scanner.nextLine());

                        if (grade < 0 || grade > 100) {
                            System.out.println(
                                "Grade must be between 0 and 100."
                            );
                            break;
                        }

                        student.addGrade(grade);

                        System.out.println("Grade added successfully.");

                    } catch (NumberFormatException exception) {
                        System.out.println("Please enter a valid number.");
                    }

                    break;

                case "3":

                    if (manager.getStudents().isEmpty()) {
                        System.out.println("No students added yet.");
                        break;
                    }

                    System.out.println("\n--- Students ---");

                    for (Student s : manager.getStudents()) {
                        System.out.printf(
                            "%s | Average: %.2f%n",
                            s.getName(),
                            s.getAverage()
                        );
                    }

                    break;

                case "4":
                    System.out.print("Enter student name: ");
                    String searchName = scanner.nextLine();

                    Student found = manager.findStudent(searchName);

                    if (found == null) {
                        System.out.println("Student not found.");
                    } else {
                        System.out.println("\nStudent: " + found.getName());
                        System.out.println("Grades: " + found.getGrades());

                        System.out.printf(
                            "Average: %.2f%n",
                            found.getAverage()
                        );
                    }

                    break;

                case "5":
                    System.out.printf(
                        "Class Average: %.2f%n",
                        manager.getClassAverage()
                    );
                    break;

                case "6":
                    Student top = manager.getTopStudent();

                    if (top == null) {
                        System.out.println("No students added yet.");
                    } else {
                        System.out.printf(
                            "Top Student: %s | Average: %.2f%n",
                            top.getName(),
                            top.getAverage()
                        );
                    }

                    break;

                case "7":
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println(
                        "Invalid option. Please choose 1-7."
                    );
            }
        }

        scanner.close();
    }
}