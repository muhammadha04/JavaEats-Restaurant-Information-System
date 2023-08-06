package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

public class Menu implements Initializable {
	private URL arg;
	private ResourceBundle arg3;
	public TextField nameSearch;

	@FXML
	private ChoiceBox<DishType> types;
	@FXML
	private ChoiceBox<Integer> qty;
	@FXML
	ListView<Component> listView;

	public ObservableList<DishType> dishes = FXCollections.observableArrayList(Utils.DishType.values());
	public ObservableList<Integer> quantity = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7);
	public ObservableList<Component> comps = FXCollections.observableArrayList();

	public TableView<Dish> tblView;
	@FXML
	private TableColumn<Dish, String> namecol;
	@FXML
	private TableColumn<Dish, Double> pricecol;
	@FXML
	private TableColumn<Dish, DishType> typecol;
	@FXML
	private TableColumn<Dish, Integer> tomcol;
	@FXML
	private TableColumn<Dish, String> compscol;
	@FXML
	private TableColumn<Dish, Integer> idcol;
	@FXML
	private CheckBox isGluten;
	@FXML
	private CheckBox isLactose;
	@FXML
	private Label lb1;
	@FXML
	public static Customer currentCus;

	private int viewModel = 0;

	ObservableList<Dish> dishList = FXCollections.observableArrayList();
	private boolean gluten;
	private boolean lactose;
	private boolean cusGluten;
	private boolean cusLactose;
	public static Dish currDish;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		int flag;
		this.qty.setValue(1);
		this.lactose = isLactose.isSelected();
		this.gluten = isGluten.isSelected();
		cusGluten = Restaurant.getInstance().getCurrCustomer().getIsSensitiveToGluten();
		cusLactose = Restaurant.getInstance().getCurrCustomer().getIsSensitiveToLactose();
		if (types.getValue() == null) {
			for (Dish d : Restaurant.getInstance().getDishes().values())
				dishList.add(d);
			tblView.setItems(dishList);
		} else {
			for (Dish d : Restaurant.getInstance().getDishes().values()) {
				if (d.getType().equals(types.getValue()) && d.getDishName().contains(nameSearch.getText())) {
					flag = 0;
					while (flag == 0) {
						if (lactose || gluten) {
							if (lactose && gluten) {
								if (!d.getGlu() && !d.getLac()) {
									dishList.addAll(d);

								}
								flag = 1;
							} else if (gluten) {
								if (!d.getGlu()) {
									dishList.addAll(d);

								}
								flag = 1;
							} // close gluten
							else {

								if (!d.getLac()) {
									dishList.addAll(d);
								}
								flag = 1;
							} // close lactose
						} else {
							dishList.addAll(d);
							flag = 1;
						}
					}
				}
			} // close while
		} // close for

		// TABLEVIEW
		types.setItems(dishes);
		qty.setItems(quantity);
		idcol.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("id"));
		namecol.setCellValueFactory(new PropertyValueFactory<Dish, String>("dishName"));
		pricecol.setCellValueFactory(new PropertyValueFactory<Dish, Double>("price"));
		typecol.setCellValueFactory(new PropertyValueFactory<Dish, DishType>("type"));
		tomcol.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("TimeToMake"));
		compscol.setCellValueFactory(new PropertyValueFactory<Dish, String>("compsString"));

		tblView.setItems(dishList);

	}

	public void btnSearch(ActionEvent actionEvent) {

		if (types.getValue() != null) {
			lactose = isLactose.isSelected();
			gluten = isGluten.isSelected();
			viewModel = 1;
			tblView.getItems().clear();
			initialize(arg, arg3);
		} else
			lb1.setVisible(true);

	}

	public void goToCart(ActionEvent actionEvent) {

	}

	public void editBtn(ActionEvent actionEvent) throws IOException {
		if (this.tblView.getSelectionModel().getSelectedItem() != null) {
			currDish = this.tblView.getSelectionModel().getSelectedItem();
//			comps.addAll(currDish.getComponenets());
//			listView.setItems(comps);
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("editDish.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 600, 300);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		}

	}

	public void removeComp(ActionEvent actionEvent) {
		if (this.tblView.getSelectionModel().getSelectedItem() != null) {
			currDish.removeComponent(this.listView.getSelectionModel().getSelectedItem());
			listView.getItems().clear();
			comps.clear();
			comps.addAll(currDish.getComponenets());
			listView.setItems(comps);
		}

	}

	public void cancel(ActionEvent actionEvent) {
	}

	public void getrelvnt(ActionEvent actionEvent) {
		viewModel = 2;
		tblView.getItems().clear();
		initialize(arg, arg3);
	}

	public void btnClear(ActionEvent actionEvent) {
		isLactose.setSelected(false);
		isGluten.setSelected(false);
		nameSearch.clear();
		types.setValue(null);
		clear();

	}

	public void clear() {
		viewModel = 0;
		tblView.getItems().clear();
		initialize(arg, arg3);
	}

	public void addBtn(ActionEvent actionEvent) {

		boolean flag = false;
		currentCus = Restaurant.getInstance().getCurrCustomer();
		
		int time = qty.getValue();
		Collection<Dish> dlist = Restaurant.getInstance().getReleventDishList(currentCus);
	
		if (currentCus.getIsBlacked() == true) {
			flag = true;
			expClass.illegalCus();
		}
		if (this.tblView.getSelectionModel().getSelectedItem() != null) {
			while (time != 0) {

				Dish d = this.tblView.getSelectionModel().getSelectedItem();
				if (!dlist.contains(d)) {
					flag = true;
					expClass.sensitivityalert();
				}
				d.setQty(qty.getValue());
				if (flag != true) {
					Restaurant.getInstance().addToCart(d);
				}
				time--;
			}
			tblView.getItems().clear();
			initialize(arg, arg3);
		} else {
			this.lb1.setVisible(true);
		}
	}

	public void removebtn(ActionEvent actionEvent) {

		Dish toDelete = this.tblView.getSelectionModel().getSelectedItem();
		Restaurant.getInstance().removeDish(toDelete);
		tblView.getItems().clear();
		initialize(arg, arg3);
	}

}
