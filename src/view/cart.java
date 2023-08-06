package view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeSet;

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

public class cart implements Initializable {

	private URL arg;
	private ResourceBundle arg3;
	public TextField nameSearch;

	@FXML
	private ChoiceBox<DishType> types;
	@FXML
	private ChoiceBox<Integer> qty;
	@FXML
	private ChoiceBox<DeliveryPerson> dps;
	public ObservableList<Integer> quantity = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7);
	public ObservableList<Dish> dishList = FXCollections.observableArrayList();
	public ObservableList<DeliveryPerson> dpsList = FXCollections.observableArrayList();
	public TableView<Dish> tblView;
	@FXML
	private TableColumn<Dish, String> namecol;
	@FXML
	private TableColumn<Dish, Double> pricecol;
	@FXML
	private TableColumn<Dish, DishType> typecol;
	@FXML
	private TableColumn<Dish, String> compscol;
	@FXML
	private Label priceLbl;
	@FXML
	private TableColumn<Dish, Integer> qtycol;
	@FXML
	Label noDps;
	boolean isDps = false;

	@FXML
	Label added;

	@FXML
	Label failed;
	@FXML
	Label notSelectedlbl;

	boolean notSelected = false;

	boolean success = false;
	boolean fail = false;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		for(DeliveryPerson dp : Restaurant.getInstance().getDeliveryPersons().values()) {
//			if(dp.getArea().getNeighberhoods().contains(Restaurant.getInstance().getCurrCustomer().getNeighberhood()))
//				dpsList.add(dp);
//		}
//		noDps.setVisible(isDps);

		if (success)
			fail = false;

		notSelectedlbl.setVisible(notSelected);
		added.setVisible(success);

		added.setVisible(success);
		failed.setVisible(fail);

		if (dpsList.size() == 0)
			isDps = true;
		else {
			isDps = false;
			dps.setItems(dpsList);
		}

		this.qty.setValue(1);
		// TABLEVIEW
		qty.setItems(quantity);
		namecol.setCellValueFactory(new PropertyValueFactory<Dish, String>("dishName"));
		pricecol.setCellValueFactory(new PropertyValueFactory<Dish, Double>("price"));
		typecol.setCellValueFactory(new PropertyValueFactory<Dish, DishType>("type"));
		compscol.setCellValueFactory(new PropertyValueFactory<Dish, String>("compsString"));
		qtycol.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("qty"));
		for (Dish d : Restaurant.getInstance().getOrdersCart().values()) {
			dishList.add(d);
		}
		double price = 0.0;
		for (Dish d : Restaurant.getInstance().getOrdersCart().values()) {
			
			price += d.getPrice();
		}
		priceLbl.setText(Double. toString(price));
		tblView.setItems(dishList);
	}

	public void btnClear(ActionEvent actionEvent) {
		notSelected = false;
		Restaurant.getInstance().getOrdersCart().clear();
		tblView.getItems().clear();
		initialize(arg, arg3);
	}

	public void removebtn(ActionEvent actionEvent) {

		Dish toDelete = this.tblView.getSelectionModel().getSelectedItem();
		Restaurant.getInstance().getOrdersCart().remove(toDelete.getId());
		tblView.getItems().clear();
		initialize(arg, arg3);
	}

	public void btnApply(ActionEvent actionEvent) {

		Dish toEdit = this.tblView.getSelectionModel().getSelectedItem();
		if (toEdit == null)
			notSelected = true;
		else
			notSelected = false;
		toEdit.setQty(qty.getValue());
		Restaurant.getInstance().replaceCartDish(this.tblView.getSelectionModel().getSelectedItem(), toEdit);
		tblView.getItems().clear();
		initialize(arg, arg3);
	}

	public void orderBtn(ActionEvent actionEvent) {
		double price = 0;
		notSelected = false;
		if (dishList.size() == 0) {

			fail = true;
expClass.emptyCart();
			initialize(arg, arg3);

		} else {
			success = true;
			Customer c = Restaurant.getInstance().getCurrCustomer();
			ArrayList<Dish> dishes = new ArrayList<>();
			dishes.addAll(Restaurant.getInstance().getOrdersCart().values());
			int maxId = 1;
			Order o;// = new Order(c, dishes, null);
			if (Restaurant.getInstance().getOrders().values().size() > 0) {
				for (Order c1 : Restaurant.getInstance().getOrders().values()) {
					if (c1.getId() > maxId)
						maxId = c1.getId();
				}

				o = new Order(maxId + 1);
				o.setCustomer(c);
				for (Dish d : Restaurant.getInstance().getOrdersCart().values()) {
					dishes.add(d);
					o.addDish(d);
					price += d.getPrice();
				}

				o.setDishesString(dishes.toString());
				o.setPrice(price);
			}

			else {
				o = new Order(c, dishes, null);
			}
			// o.setDishes(dishes);

			Restaurant.getInstance().addOrder(o);

			TreeSet<Order> ords = Restaurant.getInstance().getOrderByCustomer().get(o.getCustomer());
			if (ords == null) {
				ords = new TreeSet<Order>();
				Restaurant.getInstance().getOrderByCustomer().remove(o.getCustomer());
				
				Restaurant.getInstance().getOrderByCustomer().put(o.getCustomer(), ords);
			} else {
				ords.add(o);
				Restaurant.getInstance().getOrderByCustomer().remove(o.getCustomer());
				Restaurant.getInstance().getOrderByCustomer().put(o.getCustomer(), ords);
			}

			Restaurant.getInstance().getOrdersCart().clear();
			tblView.getItems().clear();

		}
		initialize(arg, arg3);
//		if(dps.getValue() == null && dpsList.size() > 0) dps.setValue(dpsList.get(0));
//		
//		DeliveryPerson dp = dps.getValue();
//		if(dp == null) return;
//		else if(dishes.size() == 1) {
//		ExpressDelivery ed = new ExpressDelivery(dp,dp.getArea(),false,o,LocalDate.now());
//		}

	}

}