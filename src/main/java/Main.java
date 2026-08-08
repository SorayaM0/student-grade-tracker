import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private final GradeManager manager = new GradeManager();

    private final TableView<Student> table = new TableView<>();

    private final Label classAverageLabel =
            new Label("Class Average: 0.00");

    private final Label topStudentLabel =
            new Label("Top Student: None");

    @Override
    public void start(Stage stage) {

        Label title = new Label("Student Grade Tracker");
        title.getStyleClass().add("title");

        Label subtitle = new Label(
                "Track students, grades, and class performance"
        );
        subtitle.getStyleClass().add("subtitle");


        // Input fields

        TextField nameField = new TextField();
        nameField.setPromptText("Student name");
        nameField.setPrefWidth(220);

        TextField gradeField = new TextField();
        gradeField.setPromptText("Grade (0-100)");
        gradeField.setPrefWidth(150);


        // Buttons

        Button addStudentButton =
                new Button("Add Student");

        Button addGradeButton =
                new Button("Add Grade");


        // Table

        TableColumn<Student, String> nameColumn =
                new TableColumn<>("Student");

        nameColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue().getName()
                        )
        );

        TableColumn<Student, String> averageColumn =
                new TableColumn<>("Average");

        averageColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                String.format(
                                        "%.2f",
                                        data.getValue().getAverage()
                                )
                        )
        );

        nameColumn.setPrefWidth(300);
        averageColumn.setPrefWidth(180);

        table.getColumns().addAll(
                nameColumn,
                averageColumn
        );

        table.setPrefHeight(280);


        // Add new student WITH first grade

        addStudentButton.setOnAction(event -> {

            String name =
                    nameField.getText().trim();

            String gradeText =
                    gradeField.getText().trim();

            if (name.isEmpty()) {

                showAlert(
                        "Please enter a student name."
                );

                return;
            }

            if (manager.findStudent(name) != null) {

                showAlert(
                        "That student already exists."
                );

                return;
            }

            if (gradeText.isEmpty()) {

                showAlert(
                        "Please enter a grade."
                );

                return;
            }

            try {

                double grade =
                        Double.parseDouble(
                                gradeText
                        );

                if (grade < 0 || grade > 100) {

                    showAlert(
                            "Grade must be between 0 and 100."
                    );

                    return;
                }

                manager.addStudent(name);

                Student student =
                        manager.findStudent(name);

                student.addGrade(grade);

                nameField.clear();
                gradeField.clear();

                refreshTable();

            } catch (NumberFormatException exception) {

                showAlert(
                        "Please enter a valid grade."
                );
            }
        });


        // Add another grade to existing student

        addGradeButton.setOnAction(event -> {

            Student student =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (student == null) {

                String name =
                        nameField.getText().trim();

                student =
                        manager.findStudent(name);
            }

            if (student == null) {

                showAlert(
                        "Select a student from the table first."
                );

                return;
            }

            String gradeText =
                    gradeField.getText().trim();

            if (gradeText.isEmpty()) {

                showAlert(
                        "Please enter a grade."
                );

                return;
            }

            try {

                double grade =
                        Double.parseDouble(
                                gradeText
                        );

                if (grade < 0 || grade > 100) {

                    showAlert(
                            "Grade must be between 0 and 100."
                    );

                    return;
                }

                student.addGrade(grade);

                gradeField.clear();

                refreshTable();

            } catch (NumberFormatException exception) {

                showAlert(
                        "Please enter a valid grade."
                );
            }
        });


        // When user clicks a student,
        // put their name in the text field.

        table.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldStudent, selectedStudent) -> {

                            if (selectedStudent != null) {

                                nameField.setText(
                                        selectedStudent.getName()
                                );
                            }
                        }
                );


        // Input row

        HBox inputRow =
                new HBox(
                        10,
                        nameField,
                        gradeField
                );

        inputRow.setAlignment(
                Pos.CENTER
        );


        // Button row

        HBox buttonRow =
                new HBox(
                        10,
                        addStudentButton,
                        addGradeButton
                );

        buttonRow.setAlignment(
                Pos.CENTER
        );


        // Statistics

        classAverageLabel
                .getStyleClass()
                .add("stats-label");

        topStudentLabel
                .getStyleClass()
                .add("stats-label");

        VBox statsBox =
                new VBox(
                        8,
                        classAverageLabel,
                        topStudentLabel
                );

        statsBox
                .getStyleClass()
                .add("stats-box");

        statsBox.setMaxWidth(
                Double.MAX_VALUE
        );


        // Main layout

        VBox root =
                new VBox(
                        12,
                        title,
                        subtitle,
                        inputRow,
                        buttonRow,
                        table,
                        statsBox
                );

        root.setPadding(
                new Insets(30)
        );

        root.setAlignment(
                Pos.TOP_CENTER
        );


        // Scene

        Scene scene =
                new Scene(
                        root,
                        620,
                        570
                );

        scene.getStylesheets().add(
                getClass()
                        .getResource("/style.css")
                        .toExternalForm()
        );


        // Window

        stage.setTitle(
                "Student Grade Tracker"
        );

        stage.setScene(scene);
        stage.show();
    }


    private void refreshTable() {

        table.setItems(
                FXCollections.observableArrayList(
                        manager.getStudents()
                )
        );

        table.refresh();


        classAverageLabel.setText(
                String.format(
                        "Class Average: %.2f",
                        manager.getClassAverage()
                )
        );


        Student top =
                manager.getTopStudent();

        if (top == null) {

            topStudentLabel.setText(
                    "Top Student: None"
            );

        } else {

            topStudentLabel.setText(
                    String.format(
                            "Top Student: %s (%.2f)",
                            top.getName(),
                            top.getAverage()
                    )
            );
        }
    }


    private void showAlert(String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.WARNING,
                        message
                );

        alert.setHeaderText(null);
        alert.showAndWait();
    }


    public static void main(String[] args) {

        launch(args);
    }
}