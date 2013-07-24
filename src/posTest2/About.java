package posTest2;

import java.io.IOException;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class About extends Application {

	@FXML
	private TableView deviceTable;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		//ObservableList<Device> data = FXCollections.<Device>sequence()
		
		//ObservableList l = new 
		//deviceTable.setItems(new ObservableList("abc","abc","abc","abc"));
		
		Parent root = FXMLLoader.load(getClass().getResource("gui/About.fxml"));
	    
        Scene scene = new Scene(root, 1000, 800);
        
        primaryStage.setTitle("JavaPOS POStest 2");
        
        primaryStage.setScene(scene);
        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
