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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import Model.*;
import Utils.*;

public class ComponentsCustomer implements Initializable {


	@FXML
	public TableView<Component> tblView;
	@FXML
	private TableColumn<Component, String> cname;
	@FXML
	private TableColumn<Component, Double> cprice;
	@FXML
	private TableColumn<Component, Boolean> cislac;
	@FXML
	private TableColumn<Component, Boolean> cisglu;
	@FXML
	private TableColumn<Component, Integer> idcol;

	boolean getPopular = false;

	private URL arg;
	private ResourceBundle arg3;
	ObservableList<Component> compList = FXCollections.observableArrayList();

	
	

	public void getPopularComponents(ActionEvent actionEvent) {

		this.getPopular = true;
		tblView.getItems().clear();
		initialize(arg, arg3);
	}
	
	public void clear(ActionEvent actionEvent) {

		this.getPopular = false;
		tblView.getItems().clear();
		initialize(arg, arg3);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		idcol.setCellValueFactory(new PropertyValueFactory<Component, Integer>("id"));
		cname.setCellValueFactory(new PropertyValueFactory<Component, String>("componentName"));
		cprice.setCellValueFactory(new PropertyValueFactory<Component, Double>("price"));
		cislac.setCellValueFactory(new PropertyValueFactory<Component, Boolean>("hasLactose"));
		cisglu.setCellValueFactory(new PropertyValueFactory<Component, Boolean>("hasGluten"));
		
			compList.clear();
			
			for (Component cus : Restaurant.getInstance().getPopularComponents()) {
				compList.addAll(cus);
			}
		tblView.setItems(compList);
	}

}
