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

public class cooksCustomer implements Initializable {
	@FXML
	private TableView<Cook> cookTbl;

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
	ObservableList<Cook> cookList = FXCollections.observableArrayList();
	private ObservableList<Expertise> cookExpertise = FXCollections.observableArrayList(Expertise.values());

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		expbox.setItems(cookExpertise);
		this.arg = arg0;
		this.arg3 = arg1;
		cookID.setCellValueFactory(new PropertyValueFactory<Cook, Integer>("id"));
		cookFirst.setCellValueFactory(new PropertyValueFactory<Cook, String>("firstName"));
		cookLast.setCellValueFactory(new PropertyValueFactory<Cook, String>("lastName"));
		birthDay.setCellValueFactory(new PropertyValueFactory<Cook, LocalDate>("birthDay"));
		tgender.setCellValueFactory(new PropertyValueFactory<Cook, Gender>("gender"));
		tExpertise.setCellValueFactory(new PropertyValueFactory<Cook, Expertise>("expert"));
		ischef.setCellValueFactory(new PropertyValueFactory<Cook, Boolean>("chef"));

		if (expbox.getValue() != null) {
			cookList.clear();
			for (Cook c : Restaurant.getInstance().GetCooksByExpertise(expbox.getValue())) {
				cookList.add(c);
			}

			cookTbl.setItems(cookList);

		}
	}


	public void clear(ActionEvent actionEvent) {

		expbox.setValue(null);
		cookTbl.getItems().clear();
		initialize(arg, arg3);
	}

	public void search(ActionEvent actionEvent) {

		cookTbl.getItems().clear();
		initialize(arg, arg3);
	}

}
