package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeSet;

import Model.Component;
import Model.Customer;
import Model.Delivery;
import Model.DeliveryArea;
import Model.DeliveryPerson;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class addDelivery implements Initializable {
	private URL arg;
	private ResourceBundle arg3;
	public TextField dishName;
	public TextField timetomake;

	@FXML
	private ChoiceBox<DeliveryArea> da;
	public ObservableList<DeliveryArea> daList = FXCollections.observableArrayList(Restaurant.getInstance().getAreas().values());
	@FXML
	private ChoiceBox<DeliveryPerson> dp;
	public ObservableList<DeliveryPerson> dpList = FXCollections.observableArrayList(Restaurant.getInstance().getDeliveryPersons().values());

	
	//FIRST TABLE
	public ObservableList<Order> orderslist = FXCollections.observableArrayList(Restaurant.getInstance().getOrders().values());

	@FXML
	public TableView<Order> tblView;
	@FXML
	private TableColumn<Order, Integer> orderidCol;
	@FXML
	private TableColumn<Order, Integer> cusidCol;
	@FXML
	private TableColumn<Order, String> dishesCol;
	@FXML
	private TableColumn<Order, Double> priceCol;

	//SECOND TABLE
	public ObservableList<Order> orderslist1 = FXCollections.observableArrayList();
	ArrayList<Order> newOrders = new ArrayList<Order>();
	@FXML
	public TableView<Order> tblView1;
	@FXML
	private TableColumn<Order, Integer> orderidCol1;
	@FXML
	private TableColumn<Order, Integer> cusidCol1;
	@FXML
	private TableColumn<Order, String> dishesCol1;
	@FXML
	private TableColumn<Order, Double> priceCol1;
	
	URL arg01;
	ResourceBundle arg11;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		dp.setItems(dpList);
		da.setItems(daList);
		tblView.getItems().clear();
		tblView1.getItems().clear();
		arg01 = arg0;
		arg11 = arg1;

		// FIRST ORDERS TABLE
		orderidCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id"));
		dishesCol.setCellValueFactory(new PropertyValueFactory<Order, String>("dishesString"));
		priceCol.setCellValueFactory(new PropertyValueFactory<Order, Double>("price"));
		cusidCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("cusid"));
		
		tblView.setItems(orderslist);
		
		//SECOND ORDERS TABLE
		orderidCol1.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id"));
		dishesCol1.setCellValueFactory(new PropertyValueFactory<Order, String>("dishesString"));
		priceCol1.setCellValueFactory(new PropertyValueFactory<Order, Double>("price"));
		cusidCol1.setCellValueFactory(new PropertyValueFactory<Order, Integer>("cusid"));
		
		orderslist1.addAll(newOrders);
		tblView1.setItems(orderslist1);
	}
	
	
	public void addOrder(ActionEvent actionEvent) {
		newOrders.add(this.tblView.getSelectionModel().getSelectedItem());
		tblView1.getItems().clear();
		initializeorders();

	}
	public void removeOrder(ActionEvent actionEvent) {
		Order toDelete = this.tblView1.getSelectionModel().getSelectedItem();
		newOrders.remove(toDelete);
		tblView1.getItems().clear();
		initializeorders();
	}
	public void initializeorders() {

		// dish comps
		orderidCol1.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id"));
		dishesCol1.setCellValueFactory(new PropertyValueFactory<Order, String>("dishesString"));
		priceCol1.setCellValueFactory(new PropertyValueFactory<Order, Double>("price"));
		cusidCol1.setCellValueFactory(new PropertyValueFactory<Order, Integer>("cusid"));
		orderslist1.addAll(newOrders);
		if(orderslist1!=null && orderslist1.size()>0)
		tblView1.setItems(orderslist1);

	}
	
	public void addDel(ActionEvent actionEvent) {

		int maxId = 0;
//		if (Restaurant.getInstance().getDeliveries().values().size() > 0) {
			for (Delivery c1 : Restaurant.getInstance().getDeliveries().values()) {
				if (c1.getId() > maxId)
					maxId = c1.getId();

			}
			Delivery.setIdCounter(maxId+1);
			TreeSet<Order> ts = new TreeSet<Order>();
			for(Order o : this.orderslist1) ts.add(o);
			
			
			for(Delivery d1 : Restaurant.getInstance().createAIMacine(dp.getValue(), da.getValue(), ts)) {
								
				Restaurant.getInstance().addDelivery(d1);
				DeliveryArea da = d1.getArea();
				da.addDelivery(d1);
				Restaurant.getInstance().removeDeliveryArea(d1.getArea(),da);
			}

		orderslist1.clear();
		initializeorders();
		tblView1.getItems().clear();
		initializeorders();
		initialize(arg, arg3);
	}

}
