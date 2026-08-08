import java.util.ArrayList;

public class Student {

    private final String name;
    private final ArrayList<Double> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getGrades() {
        return grades;
    }

    public void addGrade(double grade) {
        grades.add(grade);
    }

    public double getAverage() {
        if (grades.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;

        for (double grade : grades) {
            total += grade;
        }

        return total / grades.size();
    }
}