import java.util.ArrayList;

public class GradeManager {

    private final ArrayList<Student> students;

    public GradeManager() {
        students = new ArrayList<>();
    }

    public void addStudent(String name) {
        students.add(new Student(name));
    }

    public Student findStudent(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }

        return null;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public double getClassAverage() {
        if (students.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;

        for (Student student : students) {
            total += student.getAverage();
        }

        return total / students.size();
    }

    public Student getTopStudent() {
        if (students.isEmpty()) {
            return null;
        }

        Student topStudent = students.get(0);

        for (Student student : students) {
            if (student.getAverage() > topStudent.getAverage()) {
                topStudent = student;
            }
        }

        return topStudent;
    }
}