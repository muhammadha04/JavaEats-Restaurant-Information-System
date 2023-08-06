package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import Model.*;
import application.HandleFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.util.Duration;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;

public class homePageControl implements Initializable {
	public static StackPane stackPane;

	@FXML
	private StackPane contentArea;
	@FXML
	private Button signoutButton;
	@FXML
	private Button cusBtn;
	@FXML
	private Button compsBtn;
	@FXML
	public Label lb1;
	@FXML
	public Label lb2;
	@FXML
	public static Customer currentCus;
	@FXML
	private Button menuBtn;
	@FXML
	private Button orderBtn;
	@FXML
	private Button deliveryBtn;
	@FXML
	private Button daBtn;
	@FXML
	private Button dpBtn;
	@FXML
	private Button historyBtn;
	@FXML
	private Button cookBtn;
	@FXML
	private Button cookBtn1;
	@FXML
	private Button dishBtn;
	@FXML
	private Button cartBtn;
	@FXML
	private Button compsBtn1;
	@FXML
	ImageView imageView;
	@FXML
	MediaView mv;

	Button previousButton;
	
	public void setCurr(Customer c) {
		this.currentCus = c;
	}

	public void colorButton(Button currentButton){
		
		currentButton.setBackground(new Background(new BackgroundFill(Color.rgb(167,137,118), new CornerRadii(0), Insets.EMPTY)));
	}
	public void clearButton(Button previousButton) {
		previousButton.setBackground(null);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			
		previousButton = cusBtn;
		colorButton(previousButton);
		clearButton(menuBtn);
		clearButton(cartBtn);
		clearButton(daBtn);
		clearButton(deliveryBtn);
		clearButton(dishBtn);
		clearButton(orderBtn);
		clearButton(historyBtn);
		clearButton(dpBtn);
		clearButton(signoutButton);
		
		clearButton(cookBtn1);

		clearButton(cookBtn);
		
		clearButton(compsBtn1);
		
		clearButton(compsBtn);


		InputStream stream = null;
//		try {
//
//			stream = new FileInputStream("profilepic.png");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Image image = new Image(stream);
//		imageView.setImage(image);
//		imageView.setFitWidth(45);
//		imageView.setFitHeight(40);

		homePageControl.currentCus = Restaurant.getInstance().getCurrCustomer();

		if (Restaurant.getInstance().getIsAdmin()) {
			lb2.setText("ADMIN");
			cookBtn1.setVisible(false);
			compsBtn1.setVisible(false);
		} else if (currentCus != null) {
			lb2.setText(currentCus.getUsername());

			// HIDE ADMIN CONTROL BUTTONS
			compsBtn.setVisible(false);
			cusBtn.setVisible(false);
			dishBtn.setVisible(false);
			daBtn.setVisible(false);
			dpBtn.setVisible(false);
			orderBtn.setVisible(false);
			cookBtn.setVisible(false);
			deliveryBtn.setVisible(false);



			

			
		}
//		try {
//			Parent fxml = FXMLLoader.load(getClass().getResource("customerstest.fxml"));
//			contentArea.getChildren().add(fxml);
//		} catch (IOException ex) {
//			System.out.println("error?");
//		}

	}

	public void openCustomers(ActionEvent actionEvent) throws IOException {
		
		if(previousButton != null) clearButton(previousButton);
		colorButton(cusBtn);
		previousButton = cusBtn;
		
		Parent fxml = FXMLLoader.load(getClass().getResource("Customerstest.fxml"));
		contentArea.getChildren().setAll(fxml);
		fxml.translateYProperty().set(contentArea.getHeight());
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(fxml.translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}

	public void openComps(ActionEvent actionEvent) throws IOException {
		
		if(previousButton != null) clearButton(previousButton);
		colorButton(compsBtn);
		previousButton = compsBtn;
		
		Parent fxml = FXMLLoader.load(getClass().getResource("Components.fxml"));
		contentArea.getChildren().setAll(fxml);
		fxml.translateYProperty().set(contentArea.getHeight());
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(fxml.translateYProperty(), 0, Interpolator.EASE_BOTH);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();

	}
	public void openCompsCustomer(ActionEvent actionEvent) throws IOException {
		
		if(previousButton != null) clearButton(previousButton);
		colorButton(compsBtn1);
		previousButton = compsBtn1;
		
		Parent fxml = FXMLLoader.load(getClass().getResource("ComponentsCustomer.fxml"));
		contentArea.getChildren().setAll(fxml);
		fxml.translateYProperty().set(contentArea.getHeight());
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(fxml.translateYProperty(), 0, Interpolator.EASE_BOTH);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();

	}
	
	public void orderHistory(ActionEvent actionEvent) throws IOException {
		
		if(previousButton != null) clearButton(previousButton);
		colorButton(historyBtn);
		previousButton = historyBtn;
		
		Parent fxml = FXMLLoader.load(getClass().getResource("orderHistory.fxml"));
		contentArea.getChildren().setAll(fxml);
		fxml.translateYProperty().set(contentArea.getHeight());
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(fxml.translateYProperty(), 0, Interpolator.EASE_BOTH);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();

	}

	public void openDishes(ActionEvent actionEvent) throws IOException {
		
		if(previousButton != null) clearButton(previousButton);
		colorButton(dishBtn);
		previousButton = dishBtn;
		
		Parent fxml = FXMLLoader.load(getClass().getResource("Dishestest.fxml"));
		contentArea.getChildren().setAll(fxml);
		fxml.translateYProperty().set(contentArea.getHeight());
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(fxml.translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}

	public void openOrders(ActionEvent actionEvent) throws IOException {
		
		if(previousButton != null) clearButton(previousButton);
		colorButton(orderBtn);
		previousButton = orderBtn;
		
		Parent fxml = FXMLLoader.load(getClass().getResource("orders.fxml"));
		contentArea.getChildren().setAll(fxml);
		fxml.translateYProperty().set(contentArea.getHeight());
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(fxml.translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}

	public void openMenu(ActionEvent actionEvent) throws IOException {
		
		if(previousButton != null) clearButton(previousButton);
		colorButton(menuBtn);
		previousButton = menuBtn;
		
		Parent fxml = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		contentArea.getChildren().setAll(fxml);
		fxml.translateYProperty().set(contentArea.getHeight());
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(fxml.translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}

	public void DelAreas(ActionEvent actionEvent) throws IOException {
		
		if(previousButton != null) clearButton(previousButton);
		colorButton(daBtn);
		previousButton = daBtn;
		
		Parent fxml = FXMLLoader.load(getClass().getResource("DelAreas.fxml"));
		contentArea.getChildren().setAll(fxml);
		fxml.translateYProperty().set(contentArea.getHeight());
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(fxml.translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}

	public void cart(ActionEvent actionEvent) throws IOException {
		
		if(previousButton != null) clearButton(previousButton);
		colorButton(cartBtn);
		previousButton = cartBtn;
		
		Parent fxml = FXMLLoader.load(getClass().getResource("Cart.fxml"));
		contentArea.getChildren().setAll(fxml);
		fxml.translateYProperty().set(contentArea.getHeight());
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(fxml.translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}

	public void openAdd(ActionEvent actionEvent) throws IOException {
		
		if(previousButton != null) clearButton(previousButton);
		colorButton(daBtn);
		previousButton = daBtn;
		
		Parent fxml = FXMLLoader.load(getClass().getResource("addDeliveryArea.fxml"));
		contentArea.getChildren().setAll(fxml);
		fxml.translateYProperty().set(contentArea.getHeight());
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(fxml.translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
	public void Deliveries(ActionEvent actionEvent) throws IOException {
		
		if(previousButton != null) clearButton(previousButton);
		colorButton(deliveryBtn);
		previousButton = deliveryBtn;
		Parent fxml = FXMLLoader.load(getClass().getResource("Deliveries.fxml"));
		contentArea.getChildren().setAll(fxml);
		fxml.translateYProperty().set(contentArea.getHeight());
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(fxml.translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
	public void Cooks(ActionEvent actionEvent) throws IOException {
		
		if(previousButton != null) clearButton(previousButton);
		colorButton(cookBtn);
		previousButton = cookBtn;
		
		Parent fxml = FXMLLoader.load(getClass().getResource("Cook.fxml"));
		contentArea.getChildren().setAll(fxml);
		fxml.translateYProperty().set(contentArea.getHeight());
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(fxml.translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
	public void CooksCustomer(ActionEvent actionEvent) throws IOException {
		
		if(previousButton != null) clearButton(previousButton);
		colorButton(cookBtn1);
		previousButton = cookBtn1;
		
		Parent fxml = FXMLLoader.load(getClass().getResource("cooksCustomer.fxml"));
		contentArea.getChildren().setAll(fxml);
		fxml.translateYProperty().set(contentArea.getHeight());
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(fxml.translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}

	public void DelPersons(ActionEvent actionEvent) throws IOException {
		
		if(previousButton != null) clearButton(previousButton);
		colorButton(dpBtn);
		previousButton = dpBtn;
		
		Parent fxml = FXMLLoader.load(getClass().getResource("DelPersons.fxml"));
		contentArea.getChildren().setAll(fxml);
		fxml.translateYProperty().set(contentArea.getHeight());
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(fxml.translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.3), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}

	public void signout(ActionEvent actionEvent) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("logIn.fxml"));

		Scene mainScene;
		Stage stage;
		stackPane = new StackPane();
		stage = Main.mainStage;
		try {
			Parent par = FXMLLoader.load(getClass().getResource("logIn.fxml"));

			File f = new File("loginVid.mp4");
			Media m = new Media(f.toURI().toString());
			Main.mp = new MediaPlayer(m);
			mv = new MediaView(Main.mp);
			mv.minWidth(700);
			mv.minHeight(630);
			Main.mp.play();
			stackPane.getChildren().add(mv);
			stackPane.getChildren().add(par);
			mainScene = new Scene(stackPane, 1200, 750);
//			mainScene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
			Stage stage1 = (Stage) signoutButton.getScene().getWindow();
			stage1.close();
			stage.setScene(mainScene);
			stage.show();
			HandleFile.autoSaveData();

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
