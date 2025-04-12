package controller;

import domain.CircularLinkedList;
import domain.Employee;
import domain.ListException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

public class EmployeeController {

    @FXML
    private TableColumn<Employee, Date> birthdayTableColumn;

    @FXML
    private BorderPane bp;

    @FXML
    private TableView<Employee> employeesTableview;

    @FXML
    private TableColumn<Employee, String> firstNameTableColumn;

    @FXML
    private TableColumn<Employee, Integer> idTableColumn;

    @FXML
    private TableColumn<Employee, String> lastNameTableColumn;

    @FXML
    private TableColumn<Employee, String> titleTableColumn;

    private CircularLinkedList employeeList;
    private Alert alert;

    @FXML
    public void initialize() {
        this.employeeList = util.Utility.getEmployeeList();
        alert = util.FXUtility.alert("Employees List", "Display Employee");
        alert.setAlertType(Alert.AlertType.ERROR);
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        firstNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        titleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        birthdayTableColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        try{
            if(employeeList!=null && !employeeList.isEmpty()){
                for(int i=1; i<=employeeList.size(); i++) {
                    employeesTableview.getItems().add((Employee) employeeList.getNode(i).data);
                }
            }

        }catch(ListException ex){
            alert.setContentText("Employees list is empty");
            alert.showAndWait();
        }
    }

    @FXML
    void addOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/addEmployee.fxml"));
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
    void clearOnAction(ActionEvent event) {
        this.employeeList.clear();
        util.Utility.setEmployeeList(this.employeeList);
        this.alert.setContentText("The list was deleted");
        this.alert.setAlertType(Alert.AlertType.INFORMATION);
        this.alert.showAndWait();
        try {
            updateTableView();
        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void containsOnAction(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Check if Employee Exists");
        dialog.setHeaderText("Enter Employee ID");
        dialog.setContentText("ID:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                int id = Integer.parseInt(result.get());
                Employee tempEmployee = new Employee(id, "", "", "", null);

                boolean exists = employeeList.contains(tempEmployee);

                alert.setAlertType(Alert.AlertType.INFORMATION);
                if (exists) {
                    alert.setContentText("Employee with ID " + id + " exists in the list");
                } else {
                    alert.setContentText("Employee with ID " + id + " does not exist in the list");
                }
                alert.showAndWait();
            } catch (NumberFormatException e) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Please enter a valid numeric ID");
                alert.showAndWait();
            } catch (ListException e) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Error: " + e.getMessage());
                alert.showAndWait();
            }
        }
    }

    @FXML
    void getNextOnAction(ActionEvent event) {
        try {
            Employee selectedEmployee = employeesTableview.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                Object result = employeeList.getNext(selectedEmployee);

                if (result instanceof Employee) {
                    Employee nextEmployee = (Employee) result;

                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setContentText("Next employee: \n" + nextEmployee.toString());
                    alert.showAndWait();


                    employeesTableview.getSelectionModel().select(nextEmployee);
                } else {
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setContentText(result.toString());
                    alert.showAndWait();
                }
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Please select an employee from the table first");
                alert.showAndWait();
            }
        } catch (ListException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void getPrevOnAction(ActionEvent event) {
        try {
            Employee selectedEmployee = employeesTableview.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                Object result = employeeList.getPrev(selectedEmployee);

                if (result instanceof Employee) {
                    Employee prevEmployee = (Employee) result;

                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setContentText("Previous employee: \n" + prevEmployee.toString());
                    alert.showAndWait();


                    employeesTableview.getSelectionModel().select(prevEmployee);
                } else {
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setContentText(result.toString());
                    alert.showAndWait();
                }
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Please select an employee from the table first");
                alert.showAndWait();
            }
        } catch (ListException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void removeLastOnAction(ActionEvent event) {
        try {
            if (employeeList.isEmpty()) {
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setContentText("The employee list is empty");
                alert.showAndWait();
                return;
            }

            Object removedEmployee = employeeList.removeLast();
            util.Utility.setEmployeeList(this.employeeList);

            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Last employee was removed successfully:\n" + removedEmployee.toString());
            alert.showAndWait();

            updateTableView();
        } catch (ListException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void removeOnAction(ActionEvent event) {
        try {
            Employee selectedEmployee = employeesTableview.getSelectionModel().getSelectedItem();

            if (selectedEmployee != null) {
                employeeList.remove(selectedEmployee);
                util.Utility.setEmployeeList(this.employeeList);

                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Employee was removed successfully:\n" + selectedEmployee.toString());
                alert.showAndWait();

                updateTableView();
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Please select an employee from the table to remove");
                alert.showAndWait();
            }
        } catch (ListException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void sizeOnAction(ActionEvent event) {
        try {
            int listSize = employeeList.size();
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("The employee list contains " + listSize + " employee(s)");
            alert.showAndWait();
        } catch (ListException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void sortByIdOnAction(ActionEvent event) {
        try {
            if (employeeList.isEmpty()) {
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setContentText("The employee list is empty");
                alert.showAndWait();
                return;
            }

            employeeList.sort();
            util.Utility.setEmployeeList(this.employeeList);

            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("The employee list has been sorted by ID");
            alert.showAndWait();

            updateTableView(); // Update the table view
        } catch (ListException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void sortByNameOnAction(ActionEvent event) {
        try {
            if (employeeList.isEmpty()) {
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setContentText("The employee list is empty");
                alert.showAndWait();
                return;
            }

            for (int i = 1; i <= employeeList.size(); i++) {
                for (int j = i + 1; j <= employeeList.size(); j++) {
                    Employee emp1 = (Employee) employeeList.getNode(i).data;
                    Employee emp2 = (Employee) employeeList.getNode(j).data;

                    int lastNameComparison = emp1.getLastName().compareTo(emp2.getLastName());
                    if (lastNameComparison > 0 ||
                            (lastNameComparison == 0 && emp1.getFirstName().compareTo(emp2.getFirstName()) > 0)) {
                        // Swap the nodes
                        Object temp = employeeList.getNode(i).data;
                        employeeList.getNode(i).data = employeeList.getNode(j).data;
                        employeeList.getNode(j).data = temp;
                    }
                }
            }

            util.Utility.setEmployeeList(this.employeeList);

            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("The employee list has been sorted by name (last name, first name)");
            alert.showAndWait();

            updateTableView();
        } catch (ListException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }
    }

    private void updateTableView() throws ListException {
        this.employeesTableview.getItems().clear();
        this.employeeList = util.Utility.getEmployeeList();
        if(employeeList!=null && !employeeList.isEmpty()){
            for(int i=1; i<=employeeList.size(); i++) {
                this.employeesTableview.getItems().add((Employee) employeeList.getNode(i).data);
            }
        }
    }
}