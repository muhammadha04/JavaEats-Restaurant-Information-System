package view;

import java.io.File;
import java.io.IOException;

import Model.Restaurant;
import application.HandleFile;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.geometry.*;

public class Main extends Application {

	public static Scene mainScene;
	public static Stage mainStage;
	public static String[] arg;
	public static MediaPlayer mp;
	@FXML
	MediaView mv;
	@FXML
	public static StackPane stackPane;

	public void start(Stage primaryStage) {
		stackPane = new StackPane();
		mainStage = primaryStage;
		try {
			Parent par = FXMLLoader.load(getClass().getResource("logIn.fxml"));

			File f = new File("loginVid.mp4");
			Media m = new Media(f.toURI().toString());
			mp = new MediaPlayer(m);
			mv = new MediaView(mp);
			mv.minWidth(1200);
			mv.minHeight(750);
			mv.fitWidthProperty();
			mv.fitHeightProperty();
			mp.play();
			stackPane.getChildren().add(mv);


			stackPane.getChildren().add(par);
			mainScene = new Scene(stackPane,1200,750);
//			mainScene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
			primaryStage.setScene(mainScene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {

		Restaurant.getInstance();
		HandleFile.autoSaveData();
		HandleFile.loadData();
		launch(args);
	}
}
