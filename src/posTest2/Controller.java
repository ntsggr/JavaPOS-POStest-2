/*
 * Copyright 2013 NTS New Technology Systems GmbH. All Rights reserved.
 * NTS PROPRIETARY/CONFIDENTIAL. Use is subject to NTS License Agreement.
 * Address: Doernbacher Strasse 126, A-4073 Wilhering, Austria
 * Homepage: www.ntswincash.com
 */
package posTest2;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller extends Application {

	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		
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
