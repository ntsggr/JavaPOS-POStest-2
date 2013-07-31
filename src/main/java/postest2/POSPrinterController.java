/*
 * Copyright 2013 NTS New Technology Systems GmbH. All Rights reserved.
 * NTS PROPRIETARY/CONFIDENTIAL. Use is subject to NTS License Agreement.
 * Address: Doernbacher Strasse 126, A-4073 Wilhering, Austria
 * Homepage: www.ntswincash.com
 */
package postest2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import jpos.POSPrinter;
import jpos.profile.JposDevCats;

public class POSPrinterController implements Initializable {
	// COMBO BOXES
		@FXML private ComboBox<String> logicalName;
		@FXML private ComboBox<String> rotationMode;
		@FXML private ComboBox<String> mapMode;
		
		// BUTTONS
		@FXML private Button buttonOpen;
		@FXML private Button buttonClaim;
		@FXML private Button buttonRelease;
		@FXML private Button buttonStatistics;
		@FXML private Button buttonClose;
		@FXML private Button buttonFirmware;
		@FXML private Button buttonPrintNormal;
		@FXML private Button buttonPrintImmediate;
		
		// CHECKBOXES
		@FXML private CheckBox checkDeviceEnabled;
		@FXML private CheckBox checkFreezeEvents;
		@FXML private CheckBox checkAsyncMode;
		@FXML private CheckBox checkFlagWhenIdle;
		
		// RADIO BUTTONS
		@FXML private RadioButton rbReceipt;
		@FXML private RadioButton rbJournal;
		@FXML private RadioButton rbSlip;
		
		// TAB PANE
		@FXML private TabPane tabPane;
		
		// Group for the radiobuttons
		@FXML private final ToggleGroup group = new ToggleGroup();
		
		private POSPrinter posPrinter;

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			logicalName.setItems(LogicalNameGetter.getLogicalNamesByCategory(JposDevCats.POSPRINTER_DEVCAT.toString()));
			
			posPrinter = new POSPrinter();
			
			rotationMode.setValue(rotationMode.getItems().get(0));
			mapMode.setValue(mapMode.getItems().get(0));
			
			rbReceipt.setToggleGroup(group);
			rbJournal.setToggleGroup(group);
			rbSlip.setToggleGroup(group);
			rbReceipt.setSelected(true);
		}

		
		private void setUpLogicalNameComboBox(){
			logicalName.setItems(LogicalNameGetter.getLogicalNamesByCategory(JposDevCats.LINEDISPLAY_DEVCAT.toString()));
		}
		
		
		/* ************************************************************************
		 ************************* Action Handler *********************************
		 *************************************************************************/
		
		@FXML
		public void handleOpen(ActionEvent e) {
			
	    }
		
		@FXML
		public void handleClaim(ActionEvent e) {
	    }
		
		@FXML
		public void handleRelease(ActionEvent e) {
	    }
		
		@FXML
		public void handleStatistics(ActionEvent e) {
	    }
		
		@FXML
		public void handleClose(ActionEvent e) {
	    }
		
		@FXML
		public void handleFirmware(ActionEvent e) {
	    }
		
		@FXML
		public void handleDeviceEnabled(ActionEvent e) {
	    }
		
		@FXML
		public void handleFreezeEvents(ActionEvent e) {
	    }
		
		@FXML
		public void handleAsyncMode(ActionEvent e) {
	    }
		
		@FXML
		public void handleFlagWhenIdle(ActionEvent e) {
	    }
		
		@FXML
		public void handlePrintNormal(ActionEvent e) {
	    }
		
		@FXML
		public void handlePrintImmediate(ActionEvent e) {
	    }
		
		public void start(Stage primaryStage) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("gui/POSPrinter.fxml"));
	        Scene scene = new Scene(root, 1000, 800);
	        primaryStage.setTitle("JavaPOS POStest 2");
	        primaryStage.setScene(scene);
	        primaryStage.show();
		}
}
