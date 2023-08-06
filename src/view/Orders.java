package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Component;
import Model.Customer;
import Model.Delivery;
import Model.Dish;
import Model.Order;
import Model.Restaurant;
import Utils.DishType;
import Utils.Neighberhood;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Orders implements Initializable {
	private URL arg;
	private ResourceBundle arg3;
	public TextField dishName;
	public TextField timetomake;

	@FXML
	private ComboBox<DishType> types;

	public ObservableList<DishType> dishes = FXCollections.observableArrayList(Utils.DishType.values());

	public TableView<Order> tblView;
	@FXML
	private TableColumn<Order, Integer> orderidCol;
	@FXML
	private TableColumn<Order, Integer> cusidCol;
	@FXML
	private TableColumn<Order, Integer> delidCol;
	@FXML
	private TableColumn<Order, String> dishesCol;
	@FXML
	private TableColumn<Order, Double> priceCol;

	URL arg01;
	ResourceBundle arg11;
	private boolean isSearch = true;
	ObservableList<Order> orderslist = FXCollections.observableArrayList();

	public void clear(ActionEvent actionEvent) {

		this.isSearch = false;
		tblView.getItems().clear();
		initialize(arg01, arg11);
	}

	public void search(ActionEvent actionEvent) {

		this.isSearch = true;
		tblView.getItems().clear();
		initialize(arg01, arg11);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tblView.getItems().clear();
		arg01 = arg0;
		arg11 = arg1;

		// DISHES TABLE
		orderidCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id"));
		delidCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("delid"));
		dishesCol.setCellValueFactory(new PropertyValueFactory<Order, String>("dishesString"));
		priceCol.setCellValueFactory(new PropertyValueFactory<Order, Double>("price"));
		cusidCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("cusid"));
		for (Order o : Restaurant.getInstance().getOrders().values())
			orderslist.add(o);
		tblView.setItems(orderslist);
		if (isSearch)
			priceCol.setSortType(TableColumn.SortType.DESCENDING);
		tblView.getSortOrder().add(priceCol);
		tblView.sort();
	}

	public void removebtn(ActionEvent actionEvent) {

		Order toDelete = this.tblView.getSelectionModel().getSelectedItem();
		Restaurant.getInstance().removeOrder(toDelete);
		tblView.getItems().clear();
		initialize(arg, arg3);
	}

}
