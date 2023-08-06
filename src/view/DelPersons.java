package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ResourceBundle;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import Model.*;
import Utils.*;

public class DelPersons implements Initializable {

	@FXML
	private TableView<DeliveryPerson> cusTbl;
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private Label  lblAdded;
	@FXML
	private Label lblError;
	@FXML
	private Label lb3;
	@FXML
	private DatePicker DOB;
	@FXML
	private ComboBox<Gender> gender;
	@FXML
	private ComboBox<Vehicle> vehicles;
	@FXML
	private ComboBox<DeliveryArea> da;
	@FXML
	private CheckBox isLactose;
	@FXML
	private CheckBox isGluten;

	@FXML
	private TableColumn<DeliveryPerson, String> cusLast;
	@FXML
	private TableColumn<DeliveryPerson, LocalDate> birthDay;
	@FXML
	private TableColumn<DeliveryPerson, Gender> tgender;
	@FXML
	private TableColumn<DeliveryPerson, Integer> cusID;
	@FXML
	private TableColumn<DeliveryPerson, String> cusFisrt;
	@FXML
	private TableColumn<DeliveryPerson, Vehicle> colvehicle;
	@FXML
	private TableColumn<DeliveryPerson, String> dacol;
	@FXML
	private TableColumn<DeliveryPerson, Neighberhood> neighberhood;
	private URL arg;
	private ResourceBundle arg3;

	ObservableList<DeliveryPerson> daList = FXCollections.observableArrayList();
	private ObservableList<Gender> genders = FXCollections.observableArrayList(Gender.Female, Gender.Male,
			Gender.Unknown);
	private ObservableList<Vehicle> vhcls = FXCollections.observableArrayList(Vehicle.values());
	private ObservableList<DeliveryArea> das = FXCollections.observableArrayList(Restaurant.getInstance().getAreas().values());
	private boolean success = false;
	private boolean fail = false;

	public void addbtn(ActionEvent actionEvent) throws IOException {

		if(firstName.getText().equals("") || lastName.getText().equals("") || gender.getValue()==null || vehicles.getValue()==null 
				|| da.getValue()==null
				) {
			fail = true;
			expClass.fillBlank();
			initialize(arg, arg3);
		}
		 else {
			 
			 DeliveryArea da = this.da.getValue();

			DeliveryPerson c = new DeliveryPerson(firstName.getText(), lastName.getText(), DOB.getValue(),
					(Gender) gender.getValue(),vehicles.getValue(),da);
			
			 da.addDelPerson(c);
			 
			 
			 
			if (c.getId() != 1) {
				int maxId = 1;
				for (DeliveryPerson c1 : Restaurant.getInstance().getDeliveryPersons().values()) {
					if (c1.getId() > maxId)
						maxId = c1.getId();
				}
				c.setId(maxId + 1);
			}
			success  = Restaurant.getInstance().addDeliveryPerson(c,da);
			if(success) fail = false;
		}
		
				
		
		cusTbl.getItems().clear();
		initialize(arg, arg3);
	}


	public void removeBtn(ActionEvent actionEvent) {

		DeliveryPerson toDelete = this.cusTbl.getSelectionModel().getSelectedItem();
		Restaurant.getInstance().removeDeliveryPerson(toDelete);
		cusTbl.getItems().clear();
		initialize(arg, arg3);
	}

	public void blackListbtn(ActionEvent actionEvent) {
	}

	public void initialize(URL arg0, ResourceBundle arg1) {

		lblError.setVisible(fail);
		if(fail) lblAdded.setVisible(false);
		lblAdded.setVisible(success);
		this.arg = arg0;
		this.arg3 = arg1;
		DeliveryPerson.setIdCounter(Restaurant.getInstance().getDeliveryPersons().size());
		gender.setItems(genders);
		vehicles.setItems(vhcls);
		da.setItems(das);
		cusID.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, Integer>("id"));
		cusFisrt.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, String>("firstName"));
		colvehicle.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, Vehicle>("vehicle"));
		cusLast.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, String>("lastName"));
		birthDay.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, LocalDate>("birthDay"));
		tgender.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, Gender>("gender"));
		dacol.setCellValueFactory(new PropertyValueFactory<DeliveryPerson, String>("areast"));
		
		for (DeliveryPerson da : Restaurant.getInstance().getDeliveryPersons().values()) {
			daList.add(da);
		}

		cusTbl.setItems(daList);
	}
	
	

}
