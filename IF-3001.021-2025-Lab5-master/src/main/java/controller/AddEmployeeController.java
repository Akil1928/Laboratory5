package controller;

import domain.CircularLinkedList;
import domain.Employee;
import domain.ListException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AddEmployeeController {

    @FXML
    private BorderPane bp;

    @FXML
    private DatePicker datePickerBirthday;

    @FXML
    private TextField textFieldFirstName;

    @FXML
    private TextField textFieldId;

    @FXML
    private TextField textFieldLastName;

    @FXML
    private TextField textFieldTitle;

    private CircularLinkedList employeeList;
    private Alert alert;


    @FXML
    public void initialize() {
        //cargamos la lista general
        this.employeeList = util.Utility.getEmployeeList();
        alert = util.FXUtility.alert("Employee List", "Add Employee ");
    }

    @FXML
    void addOnAction(ActionEvent event) {
        try {

            if (textFieldId.getText().isEmpty() ||
                    textFieldLastName.getText().isEmpty() ||
                    textFieldFirstName.getText().isEmpty() ||
                    textFieldTitle.getText().isEmpty() ||
                    datePickerBirthday.getValue() == null) {

                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("All fields are required. Please complete all fields.");
                alert.showAndWait();
                return;
            }

            int id;
            try {
                id = Integer.parseInt(textFieldId.getText());
                if (id <= 0) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("ID must be a positive number.");
                    alert.showAndWait();
                    return;
                }
            } catch (NumberFormatException e) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("ID must be a valid number.");
                alert.showAndWait();
                return;
            }

            Employee tempEmployee = new Employee(id, "", "", "", null);
            if (!employeeList.isEmpty() && employeeList.contains(tempEmployee)) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("An employee with ID " + id + " already exists.");
                alert.showAndWait();
                return;
            }


            LocalDate localDate = datePickerBirthday.getValue();
            Date birthday = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());


            Employee newEmployee = new Employee(
                    id,
                    textFieldLastName.getText(),
                    textFieldFirstName.getText(),
                    textFieldTitle.getText(),
                    birthday
            );


            employeeList.add(newEmployee);


            util.Utility.setEmployeeList(employeeList);


            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Employee added successfully:\n" + newEmployee.toString());
            alert.showAndWait();


            clearFields();

        } catch (ListException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void birthdayOnActio(ActionEvent event) {

    }

    @FXML
    void clearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        textFieldId.clear();
        textFieldLastName.clear();
        textFieldFirstName.clear();
        textFieldTitle.clear();
        datePickerBirthday.setValue(null);
        textFieldId.requestFocus();
    }

    @FXML
    void closeOnAction(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hello-view.fxml"));


            Stage currentStage = (Stage) bp.getScene().getWindow();

            Parent root = loader.load();

            BorderPane mainBorderPane = (BorderPane) root.lookup("#mainBorderPane");

            if (mainBorderPane != null) {
                FXMLLoader employeeLoader = new FXMLLoader(getClass().getResource("/employee.fxml"));
                Parent employeeView = employeeLoader.load();
                mainBorderPane.setCenter(employeeView);
            }

            currentStage.setScene(new Scene(root));
            currentStage.sizeToScene();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de navegación");
            alert.setContentText("No se pudo cargar la vista principal: " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }
   //util.FXUtility.loadPage("ucr.lab.HelloApplication", "/employee.fxml", bp);

    @FXML
    void employeeIdOnAction(ActionEvent event) {
        if (!textFieldId.getText().isEmpty()) {
            try {
                int id = Integer.parseInt(textFieldId.getText());
                if (id <= 0) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("ID must be a positive number.");
                    alert.showAndWait();
                    textFieldId.requestFocus();
                } else {
                    try {
                        Employee tempEmployee = new Employee(id, "", "", "", null);
                        if (!employeeList.isEmpty() && employeeList.contains(tempEmployee)) {
                            alert.setAlertType(Alert.AlertType.WARNING);
                            alert.setContentText("An employee with ID " + id + " already exists.");
                            alert.showAndWait();
                        } else {
                            textFieldLastName.requestFocus();
                        }
                    } catch (ListException e) {
                        textFieldLastName.requestFocus();
                    }
                }
            } catch (NumberFormatException e) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("ID must be a valid number.");
                alert.showAndWait();
                textFieldId.requestFocus();
            }
        }
    }

    @FXML
    void firstNameOnAction(ActionEvent event) {
        if (textFieldFirstName.getText().isEmpty()) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("First name cannot be empty.");
            alert.showAndWait();
            textFieldFirstName.requestFocus();
        } else {
            textFieldTitle.requestFocus();
        }
    }

    @FXML
    void lastNameOnAction(ActionEvent event) {
        if (textFieldLastName.getText().isEmpty()) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Last name cannot be empty.");
            alert.showAndWait();
            textFieldLastName.requestFocus();
        } else {
            textFieldFirstName.requestFocus();
        }
    }

    @FXML
    void titleOnAction(ActionEvent event) {
        if (textFieldTitle.getText().isEmpty()) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Title cannot be empty.");
            alert.showAndWait();
            textFieldTitle.requestFocus();
        } else {
            datePickerBirthday.requestFocus();
        }
    }

    public void birthdayOnAction(ActionEvent actionEvent) {

    }
}