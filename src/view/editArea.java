package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import Model.*;
import Utils.*;

public class editArea implements Initializable {

	// DP 1
	@FXML
	public TableView<DeliveryPerson> dp1;
	@FXML
	private TableColumn<DeliveryPerson, Integer> dpid1;
	@FXML
	private TableColumn<DeliveryPerson, String> dpfirst1;
	@FXML
	private TableColumn<DeliveryPerson, String> dplast1;
	@FXML
	private TableColumn<DeliveryPerson, Vehicle> dpveh1;
	ObservableList<DeliveryPerson> daList1 = FXCollections.observableArrayList();
	// DP 2
	@FXML
	public TableView<DeliveryPerson> dp11;
	@FXML
	private TableColumn<DeliveryPerson, Integer> dpid11;
	@FXML
	private TableColumn<DeliveryPerson, String> dpfirst11;
	@FXML
	private TableColumn<DeliveryPerson, String> dplast11;
	@FXML
	private TableColumn<DeliveryPerson, Vehicle> dpveh11;
	@FXML
	ObservableList<DeliveryPerson> daList11 = FXCollections.observableArrayList();

	// NEIGHB
	private ObservableList<Neighberhood> neighbList = FXCollections.observableArrayList(Neighberhood.values());
	private ObservableList<Neighberhood> neighbs = FXCollections.observableArrayList();
	@FXML
	private ListView<Neighberhood> neighbview1;
	@FXML
	private ListView<Neighberhood> neighbview2;

	// DELIVERIES
	@FXML
	ListView<Delivery> allDels;
	@FXML
	ListView<Delivery> currDels;
	private ObservableList<Delivery> alldelsList = FXCollections
			.observableArrayList(Restaurant.getInstance().getDeliveries().values());
	private ObservableList<Delivery> currDelsList = FXCollections.observableArrayList();

	DeliveryArea current = delAreas.currentDA;

	public void addNeighb(ActionEvent actionEvent) {
		
		neighbs.add(this.neighbview1.getSelectionModel().getSelectedItem());
		
		ini();
	}

	public void addDel(ActionEvent actionEvent) {
		currDelsList.add(this.allDels.getSelectionModel().getSelectedItem());
		ini();

	}

	public void addDP(ActionEvent actionEvent) {
		daList11.add(this.dp1.getSelectionModel().getSelectedItem());
		ini();

	}

	public void removeNeighb(ActionEvent actionEvent) {
		neighbs.remove(this.neighbview2.getSelectionModel().getSelectedItem());
		ini();

	}

	public void removeDel(ActionEvent actionEvent) {
		currDelsList.remove(this.currDels.getSelectionModel().getSelectedItem());
		ini();

	}

	public void removeDP(ActionEvent actionEvent) {
		daList11.remove(this.dp11.getSelectionModel().getSelectedItem());
		ini();

	}
	
	public void ini() {

		
		neighbview2.setItems(neighbs);
		dp11.setItems(daList11);
		currDels.setItems(currDelsList);
		
	}
	public void apply(ActionEvent actionEvent) {
		
		DeliveryArea da = new DeliveryArea(current.getId());
		for(Delivery d : currDelsList) da.addDelivery(d);
		for(Neighberhood d : neighbs) da.addNeighberhood(d);
		for(DeliveryPerson d : daList11) da.addDelPerson(d);
		da.setAreaName(current.getAreaName());
		
		Restaurant.getInstance().removeDeliveryArea(current, da);
		((Stage) dp11.getScene().getWindow()).close();
	}
	
	public void cancel(ActionEvent actionEvent) {
		((Stage) dp11.getScene().getWindow()).close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		dp1.getItems().clear();
		neighbview1.getItems().clear();
		allDels.getItems().clear();
		
		// BEFORE DP
		dpid1.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, Integer>("id"));
		dpfirst1.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, String>("firstName"));
		dplast1.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, String>("lastName"));
		dpveh1.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, Vehicle>("vehicle"));
		for (DeliveryPerson da : Restaurant.getInstance().getDeliveryPersons().values()) {
			daList1.add(da);
		}
		dp1.setItems(daList1);
		// AFTER DP
		dpid11.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, Integer>("id"));
		dpfirst11.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, String>("firstName"));
		dplast11.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, String>("lastName"));
		dpveh11.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, Vehicle>("vehicle"));
		
		for (DeliveryPerson da : current.getDelPersons()) {
			daList11.add(da);
		}

		dp11.setItems(daList11);
		// -------------------------------------------------------------

		// BEFORE NEIGHBORHOODS

		neighbview1.setItems(neighbList);

		// AFTER NEIGHBORHOODS

		if (delAreas.currentDA != null)
			for (Neighberhood d : delAreas.currentDA.getNeighberhoods())
				neighbs.add(d);

		neighbview2.setItems(neighbs);

		// -------------------------------------------------------------

		// BEFORE DELIVERIES

		allDels.setItems(alldelsList);

		// AFTER DELIVERIES
		currDelsList.addAll(current.getDelivers());
		currDels.setItems(currDelsList);

		// -------------------------------------------------------------

	}

}
