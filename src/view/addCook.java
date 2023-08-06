package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Model.*;
import Utils.*;

public class addCook implements Initializable {
	@FXML
	private TableView<Cook> cookTbl;
	@FXML
	private CheckBox chef;
	@FXML
	private TextField firstname;
	@FXML
	private TextField lastname;
	@FXML
	private Label lblAdded;
	@FXML
	private Label lblError;
	@FXML
	private Label lb3;
	@FXML
	private DatePicker DOB;
	@FXML
	private ChoiceBox<Expertise> expertise;

	@FXML
	private ChoiceBox<Gender> gender;
	@FXML
	private ChoiceBox<Expertise> expbox;
	@FXML
	private TableColumn<Cook, Integer> cookID;
	@FXML
	private TableColumn<Cook, String> cookFirst;
	@FXML
	private TableColumn<Cook, String> cookLast;
	@FXML
	private TableColumn<Cook, LocalDate> birthDay;
	@FXML
	private TableColumn<Cook, Gender> tgender;
	@FXML
	private TableColumn<Cook, Expertise> tExpertise;
	@FXML
	private TableColumn<Cook, Boolean> ischef;
	
	private URL arg;
	private ResourceBundle arg3;
	public boolean fail = false;
	public boolean success = false;
	ObservableList<Cook> cookList = FXCollections.observableArrayList();
	private ObservableList<Gender> genders = FXCollections.observableArrayList(Gender.Female, Gender.Male,
			Gender.Unknown);
	private ObservableList<Expertise> cookExpertise = FXCollections.observableArrayList(Expertise.values());
	private boolean isexp = false;

	public void addbtn(ActionEvent actionEvent) throws IOException {

		if ("".equals(firstname.getText()) || "".equals(lastname.getText()) || DOB == null || gender == null
				|| expertise == null) {
			fail = true;
			success = false;
			expClass.fillBlank();
		} else {

			Cook c = new Cook(firstname.getText(), lastname.getText(), DOB.getValue(), (Gender) gender.getValue(),
					(Expertise) expertise.getValue(), chef.isSelected());
			if (c.getId() != 1) {
				int maxId = 1;
				for (Cook c1 : Restaurant.getInstance().getCooks().values()) {
					if (c1.getId() > maxId)
						maxId = c1.getId();
				}
				c.setId(maxId + 1);
			}
			success = Restaurant.getInstance().addCook(c);
		}
		cookTbl.getItems().clear();
		initialize(arg, arg3);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		chef.setIndeterminate(false);

		lblError.setVisible(fail);
		if (fail)
			lblAdded.setVisible(false);
		lblAdded.setVisible(success);
		expbox.setItems(cookExpertise);
		this.arg = arg0;
		this.arg3 = arg1;
		Cook.setIdCounter(Restaurant.getInstance().getCooks().size());
		gender.setItems(genders);
		expertise.setItems(cookExpertise);
		cookID.setCellValueFactory(new PropertyValueFactory<Cook, Integer>("id"));
		cookFirst.setCellValueFactory(new PropertyValueFactory<Cook, String>("firstName"));
		cookLast.setCellValueFactory(new PropertyValueFactory<Cook, String>("lastName"));
		birthDay.setCellValueFactory(new PropertyValueFactory<Cook, LocalDate>("birthDay"));
		tgender.setCellValueFactory(new PropertyValueFactory<Cook, Gender>("gender"));
		tExpertise.setCellValueFactory(new PropertyValueFactory<Cook, Expertise>("expert"));
		ischef.setCellValueFactory(new PropertyValueFactory<Cook, Boolean>("chef"));


		if (this.isexp) {
			if (expbox.getValue() != null) {
				cookList.clear();
				for (Cook c : Restaurant.getInstance().GetCooksByExpertise(expbox.getValue())) {
					cookList.add(c);
				}

			}
		}
		else {
			for (Cook c : Restaurant.getInstance().getCooks().values()) {
				cookList.add(c);
			}
		}
			cookTbl.setItems(cookList);

		}
	

	public void removebtn(ActionEvent actionEvent) {

		Cook toDelete = this.cookTbl.getSelectionModel().getSelectedItem();
		Restaurant.getInstance().removeCook(toDelete);
		cookTbl.getItems().clear();
		initialize(arg, arg3);
	}

	public void clear(ActionEvent actionEvent) {

		expbox.setValue(null);
		this.isexp = false;
		cookTbl.getItems().clear();
		initialize(arg, arg3);
	}
	public void search(ActionEvent actionEvent) {

		this.isexp = true;
	
		cookTbl.getItems().clear();
		initialize(arg, arg3);
	}

}
