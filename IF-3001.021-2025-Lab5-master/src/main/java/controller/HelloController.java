package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import ucr.lab.HelloApplication;

import java.io.IOException;

public class HelloController {

    @FXML
    private AnchorPane ap;

    @FXML
    private BorderPane bp;

    @FXML
    private Text txtMessage;

    @FXML
    private void load(String form) {
        try {
            // Intenta cargar el FXML con una ruta completa
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/"+form));

            // Asegúrate de manejar cualquier error de carga
            if (fxmlLoader.getLocation() == null) {
                System.err.println("No se puede encontrar el archivo FXML: " + form);
                return;
            }

            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace(); // Muestra el stack trace completo
            throw new RuntimeException("Error al cargar el FXML: " + form, e);
        }
    }

    @FXML
    void Exit(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    public void exampleOnMousePressed(Event event) {
        this.txtMessage.setText("Loading Example. Please wait!!!");

    }

    @FXML
    void Home(ActionEvent event) {
        this.txtMessage.setText("Laboratory 5");
        this.bp.setCenter(ap);

    }

    @FXML
    void employeesOnAction(ActionEvent event) {
        load("employee.fxml");

    }

    @FXML
    void exampleOnMousePressed(MouseEvent event) {
        this.txtMessage.setText("Loading Example. Please wait!!!");

    }

    @FXML
    void jobPositionsOnAction(ActionEvent event) {
        load("jobPositions.fxml");

    }

    @FXML
    void staffingOnAction(ActionEvent event) {
        load("staffing.fxml");
    }

}
