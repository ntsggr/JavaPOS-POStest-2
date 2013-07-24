package posTest2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller extends Application {

	@FXML
	private TableView<Device> deviceTable = new TableView<Device>();
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		ArrayList<Device> al = new ArrayList<Device>();
		Device d = new Device("Abc", "Abc", "Abc", "Abc");
		al.add(d);
		ObservableList<Device> data = FXCollections.observableList(al);
		
		deviceTable.setItems(data);
		//ObservableList l = new 
		//deviceTable.setItems(new ObservableList("abc","abc","abc","abc"));
		
		Parent root = FXMLLoader.load(getClass().getResource("gui/About.fxml"));
		ObservableList ol =root.getChildrenUnmodifiable();
		Group rootGroup = new Group();
		
		VBox vBox = new VBox();
        vBox.getChildren().add(deviceTable);
		
		
		
        Scene scene = new Scene(root, 1000, 800);
        
        //scene.addToDirtyLayoutList(rootGroup);
        
        primaryStage.setTitle("JavaPOS POStest 2");
        
        primaryStage.setScene(scene);
        
        
        
        
        
        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
