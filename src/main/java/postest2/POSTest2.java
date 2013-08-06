package postest2;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class POSTest2 extends Application {
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("gui/MainWindow.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("JavaPOS POStest 2");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	@Override
	public void stop() throws Exception {
		// TODO Close Connections

	}

	public static void main(String[] args) {
		launch(args);
	}

}
