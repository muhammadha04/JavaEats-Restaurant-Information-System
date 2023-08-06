package view;

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
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Dishes implements Initializable {
	private URL arg;
	private ResourceBundle arg3;
	public TextField dishName;
	public TextField timetomake;

	@FXML
	private ComboBox<DishType> types;

	public ObservableList<DishType> dishes = FXCollections.observableArrayList(Utils.DishType.values());

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

	// COMPS LIST
	@FXML
	public TableView<Component> tblView1;
	@FXML
	private TableColumn<Component, String> cname1;
	@FXML
	private TableColumn<Component, Double> cprice1;
	@FXML
	private TableColumn<Component, Boolean> cislac1;
	@FXML
	private TableColumn<Component, Boolean> cisglu1;
	@FXML
	private TableColumn<Component, Integer> idcol1;

	// DISH COMPS LIST
	@FXML
	public TableView<Component> tblView2;
	@FXML
	public Label pricelbl;
	@FXML
	private TableColumn<Component, String> cname2;
	@FXML
	private TableColumn<Component, Double> cprice2;
	@FXML
	private TableColumn<Component, Boolean> cislac2;
	@FXML
	private TableColumn<Component, Boolean> cisglu2;
	@FXML
	private TableColumn<Component, Integer> idcol2;
	URL arg01;
	ResourceBundle arg11;
	private boolean getProfit = false;
	private ArrayList<Component> newcomps;
	ObservableList<Component> compList1 = FXCollections.observableArrayList();
	ObservableList<Component> compList2 = FXCollections.observableArrayList();

	public void addComp(ActionEvent actionEvent) {
		newcomps.add(this.tblView1.getSelectionModel().getSelectedItem());
		tblView2.getItems().clear();
		initializecomps();
	}

	public void clear(ActionEvent actionEvent) {

		this.getProfit = false;
		tblView.getItems().clear();
		initialize(arg01, arg11);
	}

	public void getProfitRelation(ActionEvent actionEvent) {

		this.getProfit = true;
		initialize(arg01, arg11);
	}

	public void initializecomps() {

		// dish comps
		idcol2.setCellValueFactory(new PropertyValueFactory<Component, Integer>("id"));
		cname2.setCellValueFactory(new PropertyValueFactory<Component, String>("componentName"));
		cprice2.setCellValueFactory(new PropertyValueFactory<Component, Double>("price"));
		cislac2.setCellValueFactory(new PropertyValueFactory<Component, Boolean>("hasLactose"));
		cisglu2.setCellValueFactory(new PropertyValueFactory<Component, Boolean>("hasGluten"));
		if (this.newcomps != null) {
			for (Component cus : this.newcomps) {
				compList2.addAll(cus);
			}
		}
		tblView2.setItems(compList2);

		double price = 0.0;
		if (newcomps != null) {
			for (Component c : newcomps) {

				price += c.getPrice();
			}
			price = price * 3;
		}
		pricelbl.setText(Double.toString(price));
	}

	ObservableList<Dish> dishList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tblView.getItems().clear();
		arg01 = arg0;
		arg11 = arg1;
		if (newcomps != null)
			newcomps.clear();
		newcomps = new ArrayList<Component>();
		// all comps
		idcol1.setCellValueFactory(new PropertyValueFactory<Component, Integer>("id"));
		cname1.setCellValueFactory(new PropertyValueFactory<Component, String>("componentName"));
		cprice1.setCellValueFactory(new PropertyValueFactory<Component, Double>("price"));
		cislac1.setCellValueFactory(new PropertyValueFactory<Component, Boolean>("hasLactose"));
		cisglu1.setCellValueFactory(new PropertyValueFactory<Component, Boolean>("hasGluten"));
		for (Component cus : Restaurant.getInstance().getComponenets().values()) {
			compList1.addAll(cus);
		}
		tblView1.setItems(compList1);
		initializecomps();
		types.setItems(dishes);

		// DISHES TABLE
		idcol.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("id"));
		namecol.setCellValueFactory(new PropertyValueFactory<Dish, String>("dishName"));
		pricecol.setCellValueFactory(new PropertyValueFactory<Dish, Double>("price"));
		typecol.setCellValueFactory(new PropertyValueFactory<Dish, DishType>("type"));
		tomcol.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("TimeToMake"));
		compscol.setCellValueFactory(new PropertyValueFactory<Dish, String>("compsString"));

		if (this.getProfit) {
			dishList.clear();
			for (Dish d : Restaurant.getInstance().getProfitRelation()) {
				dishList.addAll(d);
			}
		} else {
			for (Dish d : Restaurant.getInstance().getDishes().values()) {
				dishList.addAll(d);
			}
		}
		tblView.setItems(dishList);
	}

	public void addDish(ActionEvent actionEvent) {
		
		boolean flag=false;
		this.getProfit = false;
		initializecomps();
		Dish d;
		int maxId = 1;
		if ("".equals(dishName.getText()) || "".equals(timetomake.getText()) || types==null) {
		
			expClass.fillBlank();
		}
		if(this.newcomps.isEmpty())
		{
			flag=true;
			expClass.noComponents();
		}
		if(flag!=true) {
		if (Restaurant.getInstance().getDishes().values().size() > 0) {
			for (Dish c1 : Restaurant.getInstance().getDishes().values()) {
				if (c1.getId() > maxId)
					maxId = c1.getId();

			}

			d = new Dish(maxId + 1);
			d.setDishName(dishName.getText());
			d.setType(types.getValue());
			d.setTimeToMake(Integer.parseInt(timetomake.getText()));

		} else {

			d = new Dish(dishName.getText(), types.getValue(), this.newcomps, Integer.parseInt(timetomake.getText()));
			for(Component c : newcomps) d.addComponent(c);

		}
		d.setPrice(calcDishPrice(this.newcomps));

		for(Component c : newcomps) d.addComponent(c);
		d.setCompsString(this.newcomps.toString());
		Restaurant.getInstance().addDish(d);
		newcomps.clear();
		tblView.getItems().clear();
		tblView1.getItems().clear();
		tblView2.getItems().clear();
		initialize(arg, arg3);
	}}

	public double calcDishPrice(ArrayList<Component> d) {
		double price = 0.0;
		for (Component c : d) {

			price += c.getPrice();
		}
		price = price * 3;
		return price;
	}

	public void removebtn(ActionEvent actionEvent) {

		Dish toDelete = this.tblView.getSelectionModel().getSelectedItem();
		Restaurant.getInstance().removeDish(toDelete);
		tblView.getItems().clear();
		initialize(arg, arg3);
	}

}
