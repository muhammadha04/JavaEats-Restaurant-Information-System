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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Model.*;
import Utils.*;

public class viewDeliveries implements Initializable {

	@FXML
	private Label expressLbl;
	@FXML
	private Label regularLbl;
	@FXML
	private Label revenueLbl;
	@FXML
	private ComboBox<DeliveryPerson> comboDP;

	@FXML
	private ComboBox<Integer> comboMonth;
	// EXPRESS TABLE
	@FXML
	private TableView<ExpressDelivery> tblView1;

	@FXML
	private TableColumn<ExpressDelivery, Integer> dpid1;
	@FXML
	private TableColumn<ExpressDelivery, Integer> daid1;
	@FXML
	private TableColumn<ExpressDelivery, Boolean> isdeli1;
	@FXML
	private TableColumn<ExpressDelivery, Integer> delid1;
	@FXML
	private TableColumn<ExpressDelivery, LocalDate> delidate1;
	@FXML
	private TableColumn<ExpressDelivery, String> order1;
	@FXML
	private TableColumn<ExpressDelivery, Double> postage1;
	// EXPRESS TABLE
	@FXML
	private TableView<RegularDelivery> tblView11;
	@FXML
	private TableColumn<RegularDelivery, Integer> delid11;
	@FXML
	private TableColumn<RegularDelivery, Integer> dpid11;
	@FXML
	private TableColumn<RegularDelivery, Integer> daid11;
	@FXML
	private TableColumn<RegularDelivery, Boolean> isdeli11;
	@FXML
	private TableColumn<RegularDelivery, LocalDate> delidate11;
	@FXML
	private TableColumn<RegularDelivery, String> order11;
	boolean isSearch = false;
	private URL arg;
	private ResourceBundle arg3;
	public boolean fail = false;
	public boolean success = false;
	ObservableList<ExpressDelivery> expressList = FXCollections.observableArrayList();
	ObservableList<RegularDelivery> regularList = FXCollections.observableArrayList();
	ObservableList<Integer> monthsList = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

	public void deliver(ActionEvent actionEvent) {

		if(this.tblView1.getSelectionModel().getSelectedItem()!=null)
		Restaurant.getInstance().deliver(this.tblView1.getSelectionModel().getSelectedItem());
		if(this.tblView11.getSelectionModel().getSelectedItem()!=null)
			Restaurant.getInstance().deliver(this.tblView11.getSelectionModel().getSelectedItem());
		
		tblView11.getItems().clear();
		tblView1.getItems().clear();
		initialize(arg, arg3);

	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		comboMonth.setItems(monthsList);
		comboMonth.setValue(1);
		comboDP.setItems(FXCollections.observableArrayList(Restaurant.getInstance().getDeliveryPersons().values()));
		expressLbl.setText(
				Integer.toString(Restaurant.getInstance().getNumberOfDeliveriesPerType().get("Express delivery")));
		regularLbl.setText(
				Integer.toString(Restaurant.getInstance().getNumberOfDeliveriesPerType().get("Regular delivery")));
		revenueLbl.setText(Double.toString(Restaurant.getInstance().revenueFromExpressDeliveries()));
		this.arg = arg0;
		this.arg3 = arg1;
		Delivery.setIdCounter(Restaurant.getInstance().getDeliveries().size());

		// EXPRESS TABLE
		delid1.setCellValueFactory(new PropertyValueFactory<ExpressDelivery, Integer>("id"));
		delidate1.setCellValueFactory(new PropertyValueFactory<ExpressDelivery, LocalDate>("deliveredDate"));
		dpid1.setCellValueFactory(new PropertyValueFactory<ExpressDelivery, Integer>("dpid"));
		daid1.setCellValueFactory(new PropertyValueFactory<ExpressDelivery, Integer>("daid"));
		isdeli1.setCellValueFactory(new PropertyValueFactory<ExpressDelivery, Boolean>("isDelivered"));
		order1.setCellValueFactory(new PropertyValueFactory<ExpressDelivery, String>("orderString"));
		postage1.setCellValueFactory(new PropertyValueFactory<ExpressDelivery, Double>("postage"));

		// REGULAR TABLE
		delid11.setCellValueFactory(new PropertyValueFactory<RegularDelivery, Integer>("id"));
		dpid11.setCellValueFactory(new PropertyValueFactory<RegularDelivery, Integer>("dpid"));
		daid11.setCellValueFactory(new PropertyValueFactory<RegularDelivery, Integer>("daid"));
		isdeli11.setCellValueFactory(new PropertyValueFactory<RegularDelivery, Boolean>("isDelivered"));
		delidate11.setCellValueFactory(new PropertyValueFactory<RegularDelivery, LocalDate>("deliveredDate"));
		order11.setCellValueFactory(new PropertyValueFactory<RegularDelivery, String>("orderString"));

		for (Delivery d : Restaurant.getInstance().getDeliveries().values()) {
			if (d instanceof RegularDelivery) {
				if (isSearch) {
					if (((RegularDelivery) d).getDeliveryPerson().equals(comboDP.getValue())
							&& ((RegularDelivery) d).getDeliveredDate().getMonthValue() == comboMonth.getValue())
						regularList.add((RegularDelivery) d);
				} else
					regularList.add((RegularDelivery) d);
				
			} else {
				if (isSearch) {
					if (((ExpressDelivery) d).getDeliveryPerson().equals(comboDP.getValue())
							&& ((ExpressDelivery) d).getDeliveredDate().getMonthValue() == comboMonth.getValue())
						expressList.add((ExpressDelivery) d);
				} else
					expressList.add((ExpressDelivery) d);
			}
		}

		tblView11.setItems(regularList);
		tblView1.setItems(expressList);
	}

	public void search(ActionEvent actionEvent) {
		tblView1.getItems().clear();
		tblView11.getItems().clear();
		isSearch = true;
		initialize(arg, arg3);
	}

	public void clear(ActionEvent actionEvent) {
		isSearch = false;
tblView1.getItems().clear();
tblView11.getItems().clear();

		initialize(arg, arg3);
	}

}
