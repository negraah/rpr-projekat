package projekat.negra.ahmetspahic;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController {

    public TableView<Vehicle> tableViewVehicle;
    public TableColumn colVehiclePlates;
    public TableColumn colVehicleModel;
    public TableColumn<Vehicle, LocalDate> colVehicleLastCheckupDate;
    public TableColumn<Vehicle,String> colVehicleOwner;
    private VehiclesDAO dao;
    private ObservableList<Vehicle> listVehicles;

    public HomeController() {
        dao = VehiclesDAO.getInstance();
        listVehicles = FXCollections.observableArrayList(dao.getVehicles());
    }

    @FXML
    public void initialize() {
        tableViewVehicle.setItems(listVehicles);
        colVehiclePlates.setCellValueFactory(new PropertyValueFactory("plates"));
        colVehicleModel.setCellValueFactory(new PropertyValueFactory("model"));
        colVehicleOwner.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOwner().getFirstName()));
       // colVehicleLastCheckupDate.setCellValueFactory(data -> new SimpleDateFormat(data.getValue().getCheckups().get(0)));
    }


    public void actionAddVehicle() throws IOException {
        Stage vehicleStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/v-details.fxml"));
        VehicleDetailsController ctrl = new VehicleDetailsController(null);
        loader.setController(ctrl);
        Parent root = loader.load();
        vehicleStage.setTitle("Customers");
        vehicleStage.setScene(new Scene(root, 600, 400));
        vehicleStage.show();
    }

    public void actionDeleteVehicle(){}

    public void actionVehicleDetails() throws IOException {
        Stage vehicleStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/v-details.fxml"));
        VehicleDetailsController ctrl = new VehicleDetailsController(tableViewVehicle.getSelectionModel().getSelectedItem());
        loader.setController(ctrl);
        Parent root = loader.load();
        vehicleStage.setTitle("Vehicle details");
        vehicleStage.setScene(new Scene(root, 600, 400));
        vehicleStage.show();
    }

    public void actionShowOwners() throws IOException {
        Stage ownerStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/owner.fxml"));
        OwnerController ctrl = new OwnerController(new OwnerModel());
        loader.setController(ctrl);
        Parent root = loader.load();
        ownerStage.setTitle("Customers");
        ownerStage.setScene(new Scene(root, 600, 400));
        ownerStage.show();
    }

    public void actionAddCheckup() throws IOException {
        Stage checkupStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/checkup.fxml"));
        CheckupController ctrl = new CheckupController(tableViewVehicle.getSelectionModel().getSelectedItem());
        loader.setController(ctrl);
        Parent root = loader.load();
        checkupStage.setTitle("Add checkup");
        checkupStage.setScene(new Scene(root, 600, 400));
        checkupStage.show();
    }


}
