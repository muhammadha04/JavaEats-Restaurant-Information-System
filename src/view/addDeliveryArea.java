package view;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.awt.Container;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
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
import application.HandleFile;

public class addDeliveryArea implements Initializable {
	@FXML
	private ListView<Neighberhood> listView;
	@FXML
	private TableView<DeliveryArea> tblView;
	@FXML
	private ListView<Neighberhood> listView1;
	@FXML
	private Button addBtn;
	@FXML
	private TableColumn<DeliveryArea, Integer> daid1;
	@FXML
	private TableColumn<DeliveryArea, String> dafirst1;
	@FXML
	private TableColumn<DeliveryArea, String> neighbString;

	@FXML
	TextField name;
	@FXML
	TextField time;

	@FXML
	Label successlbl;
	@FXML
	Label isTakenlbl;
	public boolean isTaken = false;
	public boolean success = false;
	private ObservableList<Neighberhood> daList1 = FXCollections.observableArrayList(Neighberhood.values());
	private ObservableList<Neighberhood> daList2 = FXCollections.observableArrayList();
	private ObservableList<DeliveryArea> areaList = FXCollections.observableArrayList(Restaurant.getInstance().getAreas().values());

	private URL arg1;
	private ResourceBundle arg2;

	public static ArrayList<Neighberhood> currentNeighbs = new ArrayList<Neighberhood>();

	public void addDeliveryArea(ActionEvent actionEvent) {
		isTaken = false;
		HashSet<Neighberhood> neighbs = new HashSet<>();
		neighbs.addAll(daList2);
		int maxId = 0;
		for (DeliveryArea c1 : Restaurant.getInstance().getAreas().values()) {
			if (c1.getId() > maxId)
				maxId = c1.getId();
		}
		DeliveryArea c = new DeliveryArea(maxId + 1,Integer.parseInt(this.time.getText()));
		c.setAreaName(this.name.getText());
		if(neighbs!=null && neighbs.size()>0)
		for(Neighberhood n : neighbs)
		c.addNeighberhood(n);
		success = Restaurant.getInstance().addDeliveryArea(c);
		if (success == false)
			isTaken = true;
		initialize(arg1, arg2);
	}

	public void addNeigh(ActionEvent actionEvent) {
		daList2.add(listView1.getSelectionModel().getSelectedItem());

		initialize(arg1, arg2);

	}


	public void backBtn(ActionEvent actionEvent) throws IOException {

		Stage stage1 = (Stage) name.getScene().getWindow();
		stage1.close();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DeliveryArea.setIdCounter(Restaurant.getInstance().getAreas().size());
		successlbl.setVisible(success);
		isTakenlbl.setVisible(isTaken);
		this.arg1 = arg0;
		arg2 = arg1;
		
		daid1.setCellValueFactory(new PropertyValueFactory<DeliveryArea, Integer>("id"));
		dafirst1.setCellValueFactory(new PropertyValueFactory<DeliveryArea, String>("areaName"));
		neighbString.setCellValueFactory(new PropertyValueFactory<DeliveryArea, String>("neighbString"));
		tblView.setItems(areaList);
		listView.setItems(daList2);
		listView1.setItems(daList1);
	}

}
