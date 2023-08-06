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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import Model.*;
import Utils.*;

public class Deliveries implements Initializable {

	@FXML
	BorderPane contentArea;
	private URL arg;
	private ResourceBundle arg3;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.arg = arg0;
		this.arg3 = arg1;
		Parent fxml;
		try {
			fxml = FXMLLoader.load(getClass().getResource("viewDel.fxml"));
			contentArea.getChildren().add(fxml);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void openAdd(ActionEvent actionEvent) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("addDelivery.fxml"));
		contentArea.getChildren().clear();
		contentArea.getChildren().setAll(fxml);
	}

	public void viewDel(ActionEvent actionEvent) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("viewDel.fxml"));
		contentArea.getChildren().clear();
		contentArea.getChildren().setAll(fxml);
	}

}
