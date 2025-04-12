package controller;

import domain.JobPosition;
import domain.ListException;
import domain.CircularLinkedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.Optional;

public class JobPositionController {

    @FXML
    private TableColumn<JobPosition, Integer> idTableColumn;
    @FXML
    private TableColumn<JobPosition, Double> hourlyWageTableColumn;
    @FXML
    private TableColumn<JobPosition, String> descriptionTableColumn;
    @FXML
    private TableView<JobPosition> employeesTableview;
    @FXML
    private TableColumn<JobPosition, Double> totalHoursTableColumn;
    @FXML
    private TableColumn<JobPosition, Double> monthlyWageTableColumn;
    @FXML
    private BorderPane bp;

    private Alert alert = new Alert(Alert.AlertType.NONE);
    private CircularLinkedList jobPositionList = new CircularLinkedList();
    private ObservableList<JobPosition> data = FXCollections.observableArrayList();

    // Método para inicializar la lista, pasándola desde otra clase
    public void setJobPositionList(CircularLinkedList list) {
        this.jobPositionList = list;
        loadTable();
    }

    @FXML
    public void initialize() {
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        hourlyWageTableColumn.setCellValueFactory(new PropertyValueFactory<>("hourlyWage"));
        totalHoursTableColumn.setCellValueFactory(new PropertyValueFactory<>("hourlyWage"));
        monthlyWageTableColumn.setCellValueFactory(new PropertyValueFactory<>("hourlyWage"));
        loadTable();
    }

    private void loadTable() {
        data.clear();
        try {
            for (int i = 1; i <= jobPositionList.size(); i++) {
                JobPosition jp = (JobPosition) jobPositionList.getNode(i).data;
                data.add(jp);
            }
            employeesTableview.setItems(data);
        } catch (ListException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void clearOnAction(ActionEvent actionEvent) {
        try {
            jobPositionList.clear();
            loadTable();
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Lista limpiada con éxito.");
            alert.showAndWait();
        } catch (Exception e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error al limpiar la lista.");
            alert.showAndWait();
        }
    }

    @FXML
    public void removeOnAction(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Eliminar puesto");
        dialog.setContentText("Ingrese el ID del puesto:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(idStr -> {
            try {
                int id = Integer.parseInt(idStr);
                JobPosition temp = new JobPosition(id);
                jobPositionList.remove(temp);
                loadTable();
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Puesto eliminado.");
                alert.showAndWait();
            } catch (Exception e) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Error: " + e.getMessage());
                alert.showAndWait();
            }
        });
    }

    @FXML
    void addOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/addJobPosition.fxml"));
            Parent root = loader.load();
            bp.setCenter(root);
        } catch (IOException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error al cargar el formulario de añadir empleado");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    public void sortByHourlyOnAction(ActionEvent actionEvent) {
        try {
            jobPositionList.sort();
            loadTable();
        } catch (ListException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("No se pudo ordenar: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void removeLastOnAction(ActionEvent actionEvent) {
        try {
            jobPositionList.removeLast();
            loadTable();
        } catch (ListException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void getPrevOnAction(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Anterior");
        dialog.setContentText("ID del puesto:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(idStr -> {
            try {
                int id = Integer.parseInt(idStr);
                JobPosition jp = new JobPosition(id);
                Object prev = jobPositionList.getPrev(jp);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Puesto anterior: " + prev);
                alert.showAndWait();
            } catch (Exception e) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Error: " + e.getMessage());
                alert.showAndWait();
            }
        });
    }

    @FXML
    public void getNextOnAction(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Siguiente");
        dialog.setContentText("ID del puesto:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(idStr -> {
            try {
                int id = Integer.parseInt(idStr);
                JobPosition jp = new JobPosition(id);
                Object next = jobPositionList.getNext(jp);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Puesto siguiente: " + next);
                alert.showAndWait();
            } catch (Exception e) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Error: " + e.getMessage());
                alert.showAndWait();
            }
        });
    }

    @FXML
    public void sortByIdOnAction(ActionEvent actionEvent) {
        try {
            jobPositionList.sort();
            loadTable();
        } catch (ListException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("No se pudo ordenar: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void containsOnAction(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar puesto");
        dialog.setContentText("Ingrese el ID del puesto:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(idStr -> {
            try {
                int id = Integer.parseInt(idStr);
                JobPosition jp = new JobPosition(id);
                boolean exists = jobPositionList.contains(jp);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("¿Existe? " + exists);
                alert.showAndWait();
            } catch (Exception e) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Error: " + e.getMessage());
                alert.showAndWait();
            }
        });
    }

    @FXML
    public void sizeOnAction(ActionEvent actionEvent) {
        try {
            int size = jobPositionList.size();
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Cantidad de puestos: " + size);
            alert.showAndWait();
        } catch (ListException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void addJobPosition(JobPosition newJobPosition) {

    }
}
