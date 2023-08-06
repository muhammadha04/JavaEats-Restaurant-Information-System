package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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

public class customersControl implements Initializable {

	@FXML
	private TableView<Customer> cusTbl;
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	@FXML
	private TextField repassword;
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
	private ComboBox<Neighberhood> neighb;
	@FXML
	private CheckBox isLactose;
	@FXML
	private CheckBox isGluten;
	@FXML
	private TableColumn<Customer, Integer> cusID;
	@FXML
	private TableColumn<Customer, String> cusFisrt;
	@FXML
	private TableColumn<Customer, String> cusLast;
	@FXML
	private TableColumn<Customer, LocalDate> birthDay;
	@FXML
	private TableColumn<Customer, Gender> tgender;
	@FXML
	private TableColumn<Customer, Boolean> glutenSin;
	@FXML
	private TableColumn<Customer, Boolean> lactosSin;
	@FXML
	private TableColumn<Customer, Neighberhood> neighberhood;
	@FXML
	private TableColumn<Customer, String> usernamecol;
	private URL arg;
	private ResourceBundle arg3;
	public boolean isTaken  = false;
	public boolean success = false;
	public boolean samepass = false;
	public boolean fail = false;
	ObservableList<Customer> cusList = FXCollections.observableArrayList();
	private ObservableList<Gender> genders = FXCollections.observableArrayList(Gender.Female, Gender.Male,
			Gender.Unknown);
	private ObservableList<Neighberhood> neighbs = FXCollections.observableArrayList(Neighberhood.values());

	public void addbtn(ActionEvent actionEvent) throws IOException {

		if ("".equals(firstName.getText()) || "".equals(lastName.getText()) || DOB == null || gender == null
				|| neighb == null || password.getText()==null || repassword.getText()==null) {
			fail = true;
			success = false;
			expClass.fillBlank();
		}
		if(!repassword.getText().equals(password.getText())) {
		 samepass = true;
		isTaken=false; 
		}
		else if (Restaurant.getInstance().getUsers().containsKey(username.getText())) {
			isTaken = true;
			samepass = false;
			success = false;

		} else {
			isTaken = false;
			Customer c = new Customer(firstName.getText(), lastName.getText(), DOB.getValue(),
					(Gender) gender.getValue(), (Neighberhood) neighb.getValue(), isLactose.isSelected(),
					isGluten.isSelected(), username.getText(), password.getText());
			if (c.getId() != 1) {
				int maxId = 1;
				for (Customer c1 : Restaurant.getInstance().getCustomers().values()) {
					if (c1.getId() > maxId)
						maxId = c1.getId();
				}
				c.setId(maxId + 1);
			}
			success = Restaurant.getInstance().addCustomer(c);
		}
		cusTbl.getItems().clear();
		initialize(arg, arg3);
	}

	private boolean black = false;

	public void removeBtn(ActionEvent actionEvent) {

		Customer toDelete = this.cusTbl.getSelectionModel().getSelectedItem();
		Restaurant.getInstance().removeCustomer(toDelete);
		Restaurant.getInstance().deleteUser(toDelete.getUsername());
		cusTbl.getItems().clear();
		initialize(arg, arg3);
	}

	public void blackListbtn(ActionEvent actionEvent) {
		Customer toDelete = this.cusTbl.getSelectionModel().getSelectedItem();
		Restaurant.getInstance().addCustomerToBlackList(toDelete);
		Restaurant.getInstance().getCustomers().get(toDelete.getId()).setIsBlacked(true);
		cusTbl.getItems().clear();
		initialize(arg, arg3);
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		if(fail==false) {
		black = false;
		if (isTaken) 
			success = false;
		this.arg = arg0;
		this.arg3 = arg1;
		Customer.setIdCounter(Restaurant.getInstance().getCustomers().size());
		lblError.setVisible(isTaken);
		lblAdded.setVisible(success);
		lb3.setVisible(samepass);
		gender.setItems(genders);
		neighb.setItems(neighbs);
		isLactose.setIndeterminate(false);
		isGluten.setIndeterminate(false);
		cusID.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
		cusFisrt.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
		cusLast.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
		birthDay.setCellValueFactory(new PropertyValueFactory<Customer, LocalDate>("birthDay"));
		tgender.setCellValueFactory(new PropertyValueFactory<Customer, Gender>("gender"));
		glutenSin.setCellValueFactory(new PropertyValueFactory<Customer, Boolean>("isSensitiveToGluten"));
		lactosSin.setCellValueFactory(new PropertyValueFactory<Customer, Boolean>("isSensitiveToLactose"));
		neighberhood.setCellValueFactory(new PropertyValueFactory<Customer, Neighberhood>("neighberhood"));
		usernamecol.setCellValueFactory(new PropertyValueFactory<Customer, String>("username"));
		}
	
		for (Customer cus : Restaurant.getInstance().getCustomers().values()) {
			cusList.add(cus);
		
		}
		cusTbl.setItems(cusList);
		
	}
	public void btnClick(ActionEvent actionEvent) {
		if(black) initialize(arg, arg3);
		else showBlack(actionEvent);
	}
	
	public void showBlack(ActionEvent actionEvent) {

		cusTbl.getItems().clear();
		cusID.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
		cusFisrt.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
		cusLast.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
		birthDay.setCellValueFactory(new PropertyValueFactory<Customer, LocalDate>("birthDay"));
		tgender.setCellValueFactory(new PropertyValueFactory<Customer, Gender>("gender"));
		glutenSin.setCellValueFactory(new PropertyValueFactory<Customer, Boolean>("isSensitiveToGluten"));
		lactosSin.setCellValueFactory(new PropertyValueFactory<Customer, Boolean>("isSensitiveToLactose"));
		neighberhood.setCellValueFactory(new PropertyValueFactory<Customer, Neighberhood>("neighberhood"));

		black  = true;
		for (Customer cus : Restaurant.getInstance().getBlackList()) {
			cusList.add(cus);
		}
		cusTbl.setItems(cusList);
	}
	

}
