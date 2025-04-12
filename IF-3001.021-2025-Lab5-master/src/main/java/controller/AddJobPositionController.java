package controller;

import domain.CircularLinkedList;
import domain.JobPosition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

import static util.Utility.jobPositionList;

public class AddJobPositionController {

    @FXML
    private TextField textFieldJobPosition;
    @FXML
    private TextField textFieldDescription;
    @FXML
    private TextField textFieldHourly;
    @FXML
    private BorderPane bp;

    private Alert alert = new Alert(Alert.AlertType.NONE);
    private JobPosition newJobPosition;


    @FXML
    void clearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        textFieldJobPosition.clear();
        textFieldDescription.clear();
        textFieldHourly.clear();
    }

    @FXML
    void addOnAction(ActionEvent event) {
        try {

            int id = Integer.parseInt(textFieldJobPosition.getText());
            String description = textFieldDescription.getText();
            double hourlyWage = Double.parseDouble(textFieldHourly.getText());


            JobPosition jobPosition = new JobPosition(id, description, hourlyWage);


            jobPositionList.add(jobPosition);


            showAlert(Alert.AlertType.INFORMATION, "Puesto agregado con éxito.");
            clearFields();
        } catch (NumberFormatException e) {

            showAlert(Alert.AlertType.ERROR, "Por favor, ingrese valores válidos.");
        } catch (Exception e) {

            showAlert(Alert.AlertType.ERROR, "Error al agregar el puesto. Verifique los campos e intente nuevamente.");
        }
    }

    private void showAlert(Alert.AlertType type, String message) {
        alert.setAlertType(type);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void closeOnAction(ActionEvent event) {
        try {

            Stage currentStage = (Stage) bp.getScene().getWindow();


            currentStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jobPosition.fxml"));
            Parent root = loader.load();

            JobPositionController controller = loader.getController();


            controller.addJobPosition(newJobPosition);


            Stage stage = new Stage();
            stage.setTitle("Lista de Puestos de Trabajo");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error al cerrar la ventana.");
            alert.showAndWait();
        }
    }


    @FXML
    public void descriptionOnAction(ActionEvent actionEvent) {
    }

    @FXML
    public void hourlyOnAction(ActionEvent actionEvent) {
    }

    @FXML
    public void jobPositionIdOnAction(ActionEvent actionEvent) {
    }
}

