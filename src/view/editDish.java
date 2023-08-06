package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Component;
import Model.Customer;
import Model.Dish;
import Model.Restaurant;
import Utils.DishType;
import Utils.Neighberhood;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class editDish implements Initializable {
	private URL arg;
	private ResourceBundle arg3;

	@FXML
	ListView<Component> listView;

	public ObservableList<Component> comps = FXCollections.observableArrayList();

	ObservableList<Dish> dishList = FXCollections.observableArrayList();

	public static Dish currDish;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		currDish = Menu.currDish;
		comps.addAll(currDish.getComponenets());
		listView.setItems(comps);
	}

	public void removeComp(ActionEvent actionEvent) {
		if (currDish != null) {
			currDish.removeComponent(this.listView.getSelectionModel().getSelectedItem());
			listView.getItems().clear();
			comps.clear();
			initialize(arg, arg3);
		}

	}

	public void addtoCart(ActionEvent actionEvent) {
		int time = currDish.getQty();
		if(time == 0) time = 1;
		Dish d = new Dish(currDish.getId());
		ArrayList<Component> c = new ArrayList<Component>();
		d.setTimeToMake(currDish.getTimeToMake());
		c.addAll(comps);
		d.setCompsString(c.toString());
		d.setDishName(currDish.getDishName());
		d.setType(currDish.getType());
		d.setGlu(currDish.getGlu());
		d.setLac(currDish.getLac());
		d.setQty(currDish.getQty());
		d.setPrice(currDish.getPrice());
		for(Component c1 : comps) d.addComponent(c1);	
		while (time != 0) {
			Restaurant.getInstance().addToCart(d);
			time--;
		}
		listView.getItems().clear();
		comps.clear();
		initialize(arg, arg3);
	}

	public void cancel(ActionEvent actionEvent) {
	}

}
