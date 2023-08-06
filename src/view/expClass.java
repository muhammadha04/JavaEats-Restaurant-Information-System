package view;

import Model.Customer;
import Model.Order;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class expClass {

	
	
	public static void sensitivityalert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Sensetivity Alert!");
		alert.setHeaderText("Customer is sensitive to one of the components in the dish!");
		alert.showAndWait();
	}
	
	public static void noComponents() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("No Components!");
		alert.setHeaderText("You need to choose at least one component!");
		alert.showAndWait();
	}
	
	public static void illegalCus() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Blacklist Alert!");
		alert.setHeaderText("This customer appears to be on the blacklist!");
		alert.showAndWait();
	}
	
	public static void emptyCart() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Empty Cart!");
		alert.setHeaderText("The cart must have at least one item in order to make an order!");
		alert.showAndWait();
	}
	public static void fillBlank() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Missing Information");
		alert.setHeaderText("Please fill all the blanks!");
		alert.showAndWait();
	}
	public static void reached(Order o) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("BON APITIT!");
		alert.setHeaderText(o.getId() + " had reached to Customer " + o.getCustomer().getUsername());
		alert.showAndWait();
	}
}
