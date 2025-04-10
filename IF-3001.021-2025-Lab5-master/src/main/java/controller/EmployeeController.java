package controller;

import domain.ListException;
import domain.SinglyLinkedList;
import domain.Employee;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class EmployeeController
{
    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private TableView<Employee> studentTableview; //establezco el tipo para el tableview
    @javafx.fxml.FXML
    private TableColumn<Employee, String> idTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Employee, String> nameTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Employee, Integer> ageTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Employee, String> addressTableColumn;

    //defino la lista enlazada interna
    private SinglyLinkedList studentList;
    private Alert alert; //para el manejo de alertas

    @javafx.fxml.FXML
    public void initialize() {
        //cargamos la lista general
        this.studentList = util.Utility.getEmployeeList();
        alert = util.FXUtility.alert("Student List", "Display Student");
        alert.setAlertType(Alert.AlertType.ERROR);
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ageTableColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
        addressTableColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
        try{
            if(studentList!=null && !studentList.isEmpty()){
                for(int i=1; i<=studentList.size(); i++) {
                    studentTableview.getItems().add((Employee) studentList.getNode(i).data);
                }
            }
            //this.studentTableView.setItems(observableList);
        }catch(ListException ex){
            alert.setContentText("Student list is empty");
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void clearOnAction(ActionEvent actionEvent) {
        this.studentList.clear();
        util.Utility.setEmployeeList(this.studentList); //actualizo la lista general
        this.alert.setContentText("The list was deleted");
        this.alert.setAlertType(Alert.AlertType.INFORMATION);
        this.alert.showAndWait();
        try {
            updateTableView(); //actualiza el contenido del tableview
        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void containsOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void sizeOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "addStudent.fxml", bp);
    }

    @javafx.fxml.FXML
    public void addFirstOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "addFirstStudent.fxml", bp);
    }

    @javafx.fxml.FXML
    public void removeOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void addSortedOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void getFirstOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void removeFirstOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void getLastOnAction(ActionEvent actionEvent) {
    }

    private void updateTableView() throws ListException {
        this.studentTableview.getItems().clear(); //clear table
        this.studentList = util.Utility.getEmployeeList(); //cargo la lista
        if(studentList!=null && !studentList.isEmpty()){
            for(int i=1; i<=studentList.size(); i++) {
                this.studentTableview.getItems().add((Employee) studentList.getNode(i).data);
            }
        }
    }

}