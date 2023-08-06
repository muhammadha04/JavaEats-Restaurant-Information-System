package view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Component;
import Model.Customer;
import Model.DeliveryPerson;
import Model.Dish;
import Model.ExpressDelivery;
import Model.Order;
import Model.Restaurant;
import Utils.DishType;
import Utils.Neighberhood;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class orderHistory implements Initializable {

	private URL arg;
	private ResourceBundle arg3;
	public TextField nameSearch;

	public ObservableList<Order> ordersList = FXCollections.observableArrayList();

	@FXML
	public TableView<Order> tblView;
	@FXML
	private TableColumn<Order, Integer> id;
	@FXML
	private TableColumn<Order, String> dishes;
	@FXML
	private TableColumn<Order, Double> price;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		id.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id"));
		dishes.setCellValueFactory(new PropertyValueFactory<Order, String>("dishesString"));
		price.setCellValueFactory(new PropertyValueFactory<Order, Double>("price"));
		if (Restaurant.getInstance().getOrderByCustomer().get(Restaurant.getInstance().getCurrCustomer()) != null)
//			ordersList.addAll(Restaurant.getInstance().getOrderByCustomer().get(Restaurant.getInstance().getCurrCustomer()));
			for (Order o : Restaurant.getInstance().getOrders().values()) {
				if (o.getCusid() == Restaurant.getInstance().getCurrCustomer().getId())
					ordersList.add(o);
			}
		
		tblView.setItems(ordersList);

	}

}