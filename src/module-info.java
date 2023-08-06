module JavaProject_Ex3_206539991_322465097 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;
	requires javafx.media;
	requires javafx.base;
	
	
	opens view to javafx.graphics, javafx.fxml;
    opens Model to javafx.graphics, javafx.fxml;
    exports Utils;
    exports Model;
    
}
