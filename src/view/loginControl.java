package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Model.Cook;
import Model.Customer;
import Model.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class loginControl implements Initializable {

	@FXML
	public Button loginButton;
	@FXML
	public TextField username;
	@FXML
	public PasswordField password;
	@FXML
	private BorderPane borderPane;
	@FXML
	private Label errlabel;
	public boolean flag = false;
	private URL arg;
	private ResourceBundle arg3;

	public void signin(ActionEvent actionEvent) throws IOException {

		Main.stackPane.getChildren().get(0).setVisible(false);
		Main.mp.stop();
		if (username.getText().equals("manager") && password.getText().equals("manager")) {
		//	flag = false;
			Restaurant.getInstance().setAdmin(true);
			Restaurant.getInstance().setCurrCustomer(Restaurant.getInstance().getCustomers().get(0));
			// EDIT THIS
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("homePage.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 1200, 750);
//			scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());

			Stage stage = new Stage();
			stage.setScene(scene);

			stage.show();
			Stage stage1 = (Stage) loginButton.getScene().getWindow();
			stage1.close();

		}

		else if (Restaurant.getInstance().getUsers().containsKey(username.getText())
				&& Restaurant.getInstance().getUsers().get(username.getText()).equals(password.getText())) {
		//flag = false;
			Restaurant.getInstance().setAdmin(false);
			Restaurant.getInstance().setCurrCustomer(Restaurant.getInstance().getCusFromUser(username.getText()));

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("homePage.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 1200, 750);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			Stage stage1 = (Stage) loginButton.getScene().getWindow();
			stage1.close();}
		else {
			flag = true;
			initialize(arg, arg3);
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		errlabel.setVisible(flag);
		borderPane.setStyle("-fx-background-color: transparent");

	}
}
