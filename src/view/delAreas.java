package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
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

public class delAreas implements Initializable {
	private URL arg;
	private ResourceBundle arg3;
	@FXML
	public TableView<DeliveryArea> da1;

	@FXML
	private TableColumn<DeliveryArea, Integer> daid1;
	@FXML
	private TableColumn<DeliveryPerson, Integer> cusID;
	@FXML
	private TableColumn<DeliveryPerson, String> cusFisrt;
	@FXML
	private TableColumn<DeliveryPerson, Vehicle> colvehicle;
	@FXML
	private TableColumn<DeliveryArea, String> dafirst1;
	@FXML
	ObservableList<DeliveryArea> daList1 = FXCollections.observableArrayList();
	@FXML
	private TableColumn<Delivery, Integer> delid1;
	@FXML
	private TableColumn<Delivery, LocalDate> delidate1;
	@FXML
	public TableView<DeliveryPerson> personTbl;
	@FXML
	public TableView<Delivery> delTbl;
	
	@FXML
	Label timeLbl;

	@FXML
	Label failedlbl;
	boolean failed = false;
	public static DeliveryArea currentDA;
	@FXML
	private ListView<Neighberhood> neighbsList;

	private ObservableList<Neighberhood> neighbs = FXCollections.observableArrayList();
	private ObservableList<Delivery> delList = FXCollections.observableArrayList();
	private ObservableList<DeliveryPerson> personList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if(currentDA != null) delList.addAll(Restaurant.getInstance().getAreas().get(currentDA.getId()).getDelivers());
		da1.getItems().clear();
		this.arg = arg0;
		this.arg3 = arg1;
		failedlbl.setVisible(failed);
		da1.getItems().clear();
		daid1.setCellValueFactory(new PropertyValueFactory<DeliveryArea, Integer>("id"));
		dafirst1.setCellValueFactory(new PropertyValueFactory<DeliveryArea, String>("areaName"));
		for (DeliveryArea da : Restaurant.getInstance().getAreas().values()) {
			daList1.add(da);
		}
		cusID.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, Integer>("id"));
		cusFisrt.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, String>("firstName"));
		colvehicle.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, Vehicle>("vehicle"));
		delid1.setCellValueFactory(new PropertyValueFactory<Delivery, Integer>("id"));
		delidate1.setCellValueFactory(new PropertyValueFactory<Delivery, LocalDate>("deliveredDate"));
		
//		if (currentDA != null)
//			for (DeliveryPerson d : currentDA.getDelPersons())
//				personList.add(d);
		delTbl.setItems(delList);
		personTbl.setItems(personList);
		da1.setItems(daList1);

	}

	public void removeBtn(ActionEvent actionEvent) {

		DeliveryArea toDelete = this.da1.getSelectionModel().getSelectedItem();
		if (toDelete == null)
			toDelete = currentDA;
		if (toDelete == null)
			failed = true;
		else {
			failed = false;
			Restaurant.getInstance().getAreas().remove(toDelete.getId());
			da1.getItems().clear();

		}
		initialize(arg, arg3);
	}

	public void openAdd(ActionEvent actionEvent) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("addDeliveryArea.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 700, 630);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();

	}

	public void editBtn(ActionEvent actionEvent) throws IOException {
		currentDA = this.da1.getSelectionModel().getSelectedItem();
		if (currentDA == null)
			failed = true;
		else {
			failed = false;
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("editArea.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 700, 630);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		}
		initialize(arg, arg3);
	}

	public void showDetails(ActionEvent actionEvent) throws IOException {
		neighbs.clear();
		personTbl.getItems().clear();
		delTbl.getItems().clear();
		currentDA = da1.getSelectionModel().getSelectedItem();
		if (currentDA == null)
			failed = true;
		else {

			failed = false;
			timeLbl.setText(Integer.toString(currentDA.getDeliverTime()));
			for (Neighberhood d : currentDA.getNeighberhoods())
				neighbs.add(d);

			for (Delivery d : currentDA.getDelivers())
				delList.add(d);

			for (DeliveryPerson d : currentDA.getDelPersons())
				personList.add(d);

			neighbsList.setItems(neighbs);
			personTbl.setItems(personList);
			delTbl.setItems(delList);
		}
		initialize(arg, arg3);

	}

}
