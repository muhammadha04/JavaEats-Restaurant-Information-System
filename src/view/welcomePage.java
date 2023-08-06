package view;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Restaurant;
import application.HandleFile;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class welcomePage implements Initializable {
	@FXML
	MediaView mv;
	@FXML
	StackPane stackPane;
	@FXML Button b1;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		b1 = new Button("test");
        File f = new File("C:\\Users\\User\\Desktop\\oop\\test.mp4");
        Media m = new Media(f.toURI().toString());
        stackPane.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 10;");
        MediaPlayer mp = new MediaPlayer(m);
        mv = new MediaView(mp);
	    stackPane.getChildren().add(mv);
        stackPane.getChildren().add(b1);


        mp.play();		
	}
}
