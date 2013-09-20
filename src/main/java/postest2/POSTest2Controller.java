package postest2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.util.Callback;

public class POSTest2Controller implements Initializable {

	// Device panels
	public Parent About = null;
	public Parent ConfiguredDevices = null;
	public Parent Belt = null;
	public Parent BillAcceptor = null;
	public Parent BillDispenser = null;
	public Parent Biometrics = null;
	public Parent BumpBar = null;
	public Parent CashChanger = null;
	public Parent CashDrawer = null;
	public Parent CAT = null;
	public Parent CheckScanner = null;
	public Parent CoinAcceptor = null;
	public Parent CoinDispenser = null;
	public Parent ElectronicJournal = null;
	public Parent ElectronicValueRW = null;
	public Parent FiscalPrinter = null;
	public Parent Gate = null;
	public Parent HardTotals = null;
	public Parent ImageScanner = null;
	public Parent ItemDispenser = null;
	public Parent Keylock = null;
	public Parent Lights = null;
	public Parent LineDisplay = null;
	public Parent MICR = null;
	public Parent MotionSensor = null;
	public Parent MSR = null;
	public Parent PINPad = null;
	public Parent PointCardRW = null;
	public Parent POSKeyboard = null;
	public Parent POSPower = null;
	public Parent POSPrinter = null;
	public Parent RemoteOrderDisplay = null;
	public Parent RFIDScanner = null;
	public Parent Scale = null;
	public Parent Scanner = null;
	public Parent SignatureCapture = null;
	public Parent SmartCardRW = null;
	public Parent ToneIndicator = null;

	// contains "About" and "Configured devices"
	@FXML
	public ListView<String> listGeneral;

	// contains and shows the available devices
	@FXML
	public ListView<String> listAllDevices;

	// using the "favoriteDevices" it contains and shows the favorite devices
	@FXML
	transient public ListView<String> listFavorites;

	// the main window is divided into two panels. The right panel holds the
	// panel of each clicked device
	@FXML
	public AnchorPane anchorPaneRight;

	// contains and shows the available devices
	@FXML
	public ObservableList<String> favoriteDevices;

	public List<String> storeFavorites;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		clearPathFile();
		loadDevicePanels();
		favoriteDevices = FXCollections.observableArrayList();

		File f = new File("listOfFavoriteDevices.dat");
		if (f.exists()) {
			storeFavorites = retrieveFavorites();
		} else {
			storeFavorites = new ArrayList<String>();
		}

		anchorPaneRight.getChildren().clear();
		anchorPaneRight.getChildren().addAll(About);

		listGeneral.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
				if (new_val != null && new_val.equals("About")) {
					anchorPaneRight.getChildren().clear();
					anchorPaneRight.getChildren().addAll(About);
				} else if (new_val != null && new_val.equals("ConfiguredDevices")) {
					anchorPaneRight.getChildren().clear();
					anchorPaneRight.getChildren().addAll(ConfiguredDevices);
				}
				listGeneral.getSelectionModel().clearSelection();
			}
		});

		// The setCellFactory method redefines the implementation of the list
		// cell
		listAllDevices.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new XCell();
			}
		});

		// Show the respective device panel for each selected item from the list
		// "All devices"
		listAllDevices.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
				if (new_val != null) {
					setPanel(new_val);
				}
				listAllDevices.getSelectionModel().clearSelection();
			}
		});

		// Show the respective device panel for each selected item from the list
		// "Favorites"
		listFavorites.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
				if (new_val != null) {
					setPanel(new_val);
				}
				listFavorites.getSelectionModel().clearSelection();
			}
		});

	}

	// Add a checkbox for each ListView item.
	class XCell extends ListCell<String> {

		HBox hbox = new HBox();
		Label label = new Label("(empty)");
		Pane pane = new Pane();
		CheckBox checkBox = new CheckBox();
		String lastItem;

		public XCell() {
			super();
			hbox.getChildren().addAll(label, pane, checkBox);
			HBox.setHgrow(pane, Priority.ALWAYS);

			checkBox.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if (favoriteDevices.contains(lastItem)) {
						favoriteDevices.remove(lastItem);
						listFavorites.setItems(favoriteDevices);
						storeFavorites.remove(lastItem);
						listFavorites.setPrefHeight(listFavorites.getPrefHeight() - 36.0);
						saveFavorites();
					} else {
						favoriteDevices.add(lastItem);
						listFavorites.setItems(favoriteDevices);
						listFavorites.setPrefHeight(listFavorites.getPrefHeight() + 36.0);
						storeFavorites.add(lastItem);
						saveFavorites();
					}
				}
			});

		}

		@Override
		protected void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			// setText(null); // No text in label of super class
			if (empty) {
				lastItem = null;
				setGraphic(null);
			} else {
				lastItem = item;
				label.setText(item != null ? item : "<null>");
				setGraphic(hbox);
				if (!storeFavorites.isEmpty() && storeFavorites.contains(item)) {
					checkBox.setSelected(true);
					favoriteDevices.add(lastItem);
					listFavorites.setPrefHeight(listFavorites.getPrefHeight() + 36.0);
					listFavorites.setItems(favoriteDevices);
				}
			}
		}

	} // end of XCell class

	public void saveFavorites() {
		try {
			FileOutputStream fout = new FileOutputStream("listOfFavoriteDevices.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(storeFavorites);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List retrieveFavorites() {
		List<String> list = null;
		try {
			FileInputStream fin = new FileInputStream("listOfFavoriteDevices.dat");
			ObjectInputStream ois = new ObjectInputStream(fin);
			list = (ArrayList<String>) ois.readObject();

			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private void loadDevicePanels() {
		try {
			About = FXMLLoader.load(getClass().getResource("gui/About.fxml"));
			ConfiguredDevices = FXMLLoader.load(getClass().getResource("gui/ConfiguredDevices.fxml"));
			Belt = FXMLLoader.load(getClass().getResource("gui/Belt.fxml"));
			BillAcceptor = FXMLLoader.load(getClass().getResource("gui/BillAcceptor.fxml"));
			BillDispenser = FXMLLoader.load(getClass().getResource("gui/BillDispenser.fxml"));
			Biometrics = FXMLLoader.load(getClass().getResource("gui/Biometrics.fxml"));
			BumpBar = FXMLLoader.load(getClass().getResource("gui/BumpBar.fxml"));
			CashChanger = FXMLLoader.load(getClass().getResource("gui/CashChanger.fxml"));
			CashDrawer = FXMLLoader.load(getClass().getResource("gui/CashDrawer.fxml"));
			CAT = FXMLLoader.load(getClass().getResource("gui/CAT.fxml"));
			CheckScanner = FXMLLoader.load(getClass().getResource("gui/CheckScanner.fxml"));
			CoinAcceptor = FXMLLoader.load(getClass().getResource("gui/CoinAcceptor.fxml"));
			CoinDispenser = FXMLLoader.load(getClass().getResource("gui/CoinDispenser.fxml"));
			ElectronicJournal = FXMLLoader.load(getClass().getResource("gui/ElectronicJournal.fxml"));
			ElectronicValueRW = FXMLLoader.load(getClass().getResource("gui/ElectronicValueRW.fxml"));
			FiscalPrinter = FXMLLoader.load(getClass().getResource("gui/FiscalPrinter.fxml"));
			Gate = FXMLLoader.load(getClass().getResource("gui/Gate.fxml"));
			HardTotals = FXMLLoader.load(getClass().getResource("gui/HardTotals.fxml"));
			ImageScanner = FXMLLoader.load(getClass().getResource("gui/ImageScanner.fxml"));
			ItemDispenser = FXMLLoader.load(getClass().getResource("gui/ItemDispenser.fxml"));
			Keylock = FXMLLoader.load(getClass().getResource("gui/Keylock.fxml"));
			Lights = FXMLLoader.load(getClass().getResource("gui/Lights.fxml"));
			LineDisplay = FXMLLoader.load(getClass().getResource("gui/LineDisplay.fxml"));
			MICR = FXMLLoader.load(getClass().getResource("gui/MICR.fxml"));
			MotionSensor = FXMLLoader.load(getClass().getResource("gui/MotionSensor.fxml"));
			MSR = FXMLLoader.load(getClass().getResource("gui/MSR.fxml"));
			PINPad = FXMLLoader.load(getClass().getResource("gui/PINPad.fxml"));
			PointCardRW = FXMLLoader.load(getClass().getResource("gui/PointCardRW.fxml"));
			POSKeyboard = FXMLLoader.load(getClass().getResource("gui/POSKeyboard.fxml"));
			POSPower = FXMLLoader.load(getClass().getResource("gui/POSPower.fxml"));
			POSPrinter = FXMLLoader.load(getClass().getResource("gui/POSPrinter.fxml"));
			RemoteOrderDisplay = FXMLLoader.load(getClass().getResource("gui/RemoteOrderDisplay.fxml"));
			RFIDScanner = FXMLLoader.load(getClass().getResource("gui/RFIDScanner.fxml"));
			Scale = FXMLLoader.load(getClass().getResource("gui/Scale.fxml"));
			Scanner = FXMLLoader.load(getClass().getResource("gui/Scanner.fxml"));
			SignatureCapture = FXMLLoader.load(getClass().getResource("gui/SignatureCapture.fxml"));
			SmartCardRW = FXMLLoader.load(getClass().getResource("gui/SmartCardRW.fxml"));
			ToneIndicator = FXMLLoader.load(getClass().getResource("gui/ToneIndicator.fxml"));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// Set the panel for each clicked device

	private void setPanel(String panel) {
		if (panel.equals("Belt")) {
			loadDevicePanelByName("Belt");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(Belt);
		} else if (panel.equals("BillAcceptor")) {
			loadDevicePanelByName("BillAcceptor");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(BillAcceptor);
		} else if (panel.equals("BillDispenser")) {
			loadDevicePanelByName("BillDispenser");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(BillDispenser);
		} else if (panel.equals("Biometrics")) {
			loadDevicePanelByName("Biometrics");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(Biometrics);
		} else if (panel.equals("BumpBar")) {
			loadDevicePanelByName("BumpBar");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(BumpBar);
		} else if (panel.equals("CashChanger")) {
			loadDevicePanelByName("CashChanger");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(CashChanger);
		} else if (panel.equals("CashDrawer")) {
			loadDevicePanelByName("CashDrawer");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(CashDrawer);
		} else if (panel.equals("CAT")) {
			loadDevicePanelByName("CAT");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(CAT);
		} else if (panel.equals("CheckScanner")) {
			loadDevicePanelByName("CheckScanner");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(CheckScanner);
		} else if (panel.equals("CoinAcceptor")) {
			loadDevicePanelByName("CoinAcceptor");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(CoinAcceptor);
		} else if (panel.equals("CoinDispenser")) {
			loadDevicePanelByName("CoinDispenser");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(CoinDispenser);
		} else if (panel.equals("ElectronicJournal")) {
			loadDevicePanelByName("ElectronicJournal");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(ElectronicJournal);
		} else if (panel.equals("ElectronicValueRW")) {
			loadDevicePanelByName("ElectronicValueRW");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(ElectronicValueRW);
		} else if (panel.equals("FiscalPrinter")) {
			loadDevicePanelByName("FiscalPrinter");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(FiscalPrinter);
		} else if (panel.equals("Gate")) {
			loadDevicePanelByName("Gate");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(Gate);
		} else if (panel.equals("HardTotals")) {
			loadDevicePanelByName("HardTotals");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(HardTotals);
		} else if (panel.equals("ImageScanner")) {
			loadDevicePanelByName("ImageScanner");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(ImageScanner);
		} else if (panel.equals("ItemDispenser")) {
			loadDevicePanelByName("ItemDispenser");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(ItemDispenser);
		} else if (panel.equals("Keylock")) {
			loadDevicePanelByName("Keylock");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(Keylock);
		} else if (panel.equals("Lights")) {
			loadDevicePanelByName("Lights");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(Lights);
		} else if (panel.equals("LineDisplay")) {
			loadDevicePanelByName("LineDisplay");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(LineDisplay);
		} else if (panel.equals("MICR")) {
			loadDevicePanelByName("MICR");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(MICR);
		} else if (panel.equals("MotionSensor")) {
			loadDevicePanelByName("MotionSensor");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(MotionSensor);
		} else if (panel.equals("MSR")) {
			loadDevicePanelByName("MSR");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(MSR);
		} else if (panel.equals("PINPad")) {
			loadDevicePanelByName("PINPad");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(PINPad);
		} else if (panel.equals("PointCardRW")) {
			loadDevicePanelByName("PointCardRW");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(PointCardRW);
		} else if (panel.equals("POSKeyboard")) {
			loadDevicePanelByName("POSKeyboard");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(POSKeyboard);
		} else if (panel.equals("POSPower")) {
			loadDevicePanelByName("POSPower");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(POSPower);
		} else if (panel.equals("POSPrinter")) {
			loadDevicePanelByName("POSPrinter");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(POSPrinter);
		} else if (panel.equals("RemoteOrderDisplay")) {
			loadDevicePanelByName("RemoteOrderDisplay");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(RemoteOrderDisplay);
		} else if (panel.equals("RFIDScanner")) {
			loadDevicePanelByName("RFIDScanner");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(RFIDScanner);
		} else if (panel.equals("Scale")) {
			loadDevicePanelByName("Scale");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(Scale);
		} else if (panel.equals("Scanner")) {
			loadDevicePanelByName("Scanner");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(Scanner);
		} else if (panel.equals("SignatureCapture")) {
			loadDevicePanelByName("SignatureCapture");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(SignatureCapture);
		} else if (panel.equals("SmartCardRW")) {
			loadDevicePanelByName("SmartCardRW");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(SmartCardRW);
		} else if (panel.equals("ToneIndicator")) {
			loadDevicePanelByName("ToneIndicator");
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(ToneIndicator);
		}
	}
	
	private void loadDevicePanelByName(String panelName) {
		try {
			switch(panelName){
				case "About":
					About = FXMLLoader.load(getClass().getResource("gui/About.fxml"));
				case "ConfiguredDevices":
					ConfiguredDevices = FXMLLoader.load(getClass().getResource("gui/ConfiguredDevices.fxml"));
				case "Belt":
					Belt = FXMLLoader.load(getClass().getResource("gui/Belt.fxml"));
				case "BillAcceptor":
					BillAcceptor = FXMLLoader.load(getClass().getResource("gui/BillAcceptor.fxml"));
				case "BillDispenser":
					BillDispenser = FXMLLoader.load(getClass().getResource("gui/BillDispenser.fxml"));
				case "Biometrics":
					Biometrics = FXMLLoader.load(getClass().getResource("gui/Biometrics.fxml"));
				case "BumpBar":
					BumpBar = FXMLLoader.load(getClass().getResource("gui/BumpBar.fxml"));
				case "CashChanger":
					CashChanger = FXMLLoader.load(getClass().getResource("gui/CashChanger.fxml"));
				case "CashDrawer":
					CashDrawer = FXMLLoader.load(getClass().getResource("gui/CashDrawer.fxml"));
				case "CAT":
					CAT = FXMLLoader.load(getClass().getResource("gui/CAT.fxml"));
				case "CheckScanner":
					CheckScanner = FXMLLoader.load(getClass().getResource("gui/CheckScanner.fxml"));
				case "CoinAcceptor":
					CoinAcceptor = FXMLLoader.load(getClass().getResource("gui/CoinAcceptor.fxml"));
				case "CoinDispenser":
					CoinDispenser = FXMLLoader.load(getClass().getResource("gui/CoinDispenser.fxml"));
				case "ElectronicJournal":
					ElectronicJournal = FXMLLoader.load(getClass().getResource("gui/ElectronicJournal.fxml"));
				case "ElectronicValueRW":
					ElectronicValueRW = FXMLLoader.load(getClass().getResource("gui/ElectronicValueRW.fxml"));
				case "FiscalPrinter":
					FiscalPrinter = FXMLLoader.load(getClass().getResource("gui/FiscalPrinter.fxml"));
				case "Gate":
					Gate = FXMLLoader.load(getClass().getResource("gui/Gate.fxml"));
				case "HardTotals":
					HardTotals = FXMLLoader.load(getClass().getResource("gui/HardTotals.fxml"));
				case "ImageScanner":
					ImageScanner = FXMLLoader.load(getClass().getResource("gui/ImageScanner.fxml"));
				case "ItemDispenser":
					ItemDispenser = FXMLLoader.load(getClass().getResource("gui/ItemDispenser.fxml"));
				case "Keylock":
					Keylock = FXMLLoader.load(getClass().getResource("gui/Keylock.fxml"));
				case "Lights":
					Lights = FXMLLoader.load(getClass().getResource("gui/Lights.fxml"));
				case "LineDisplay":
					LineDisplay = FXMLLoader.load(getClass().getResource("gui/LineDisplay.fxml"));
				case "MICR":
					MICR = FXMLLoader.load(getClass().getResource("gui/MICR.fxml"));
				case "MotionSensor":
					MotionSensor = FXMLLoader.load(getClass().getResource("gui/MotionSensor.fxml"));
				case "MSR":
					MSR = FXMLLoader.load(getClass().getResource("gui/MSR.fxml"));
				case "PINPad":
					PINPad = FXMLLoader.load(getClass().getResource("gui/PINPad.fxml"));
				case "PointCardRW":
					PointCardRW = FXMLLoader.load(getClass().getResource("gui/PointCardRW.fxml"));
				case "POSKeyboard":
					POSKeyboard = FXMLLoader.load(getClass().getResource("gui/POSKeyboard.fxml"));
				case "POSPower":
					POSPower = FXMLLoader.load(getClass().getResource("gui/POSPower.fxml"));
				case "POSPrinter":
					POSPrinter = FXMLLoader.load(getClass().getResource("gui/POSPrinter.fxml"));
				case "RemoteOrderDisplay":
					RemoteOrderDisplay = FXMLLoader.load(getClass().getResource("gui/RemoteOrderDisplay.fxml"));
				case "RFIDScanner":
					RFIDScanner = FXMLLoader.load(getClass().getResource("gui/RFIDScanner.fxml"));
				case "Scale":
					Scale = FXMLLoader.load(getClass().getResource("gui/Scale.fxml"));
				case "Scanner":
					Scanner = FXMLLoader.load(getClass().getResource("gui/Scanner.fxml"));
				case "SignatureCapture":
					SignatureCapture = FXMLLoader.load(getClass().getResource("gui/SignatureCapture.fxml"));
				case "SmartCardRW":
					SmartCardRW = FXMLLoader.load(getClass().getResource("gui/SmartCardRW.fxml"));
				case "ToneIndicator":
					ToneIndicator = FXMLLoader.load(getClass().getResource("gui/ToneIndicator.fxml"));
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private void clearPathFile(){
		File f = new File("pathToJposEntries.dat");
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		//Clear file and write new path!
		try(PrintWriter writer = new PrintWriter(f)){
			writer.print("");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

}
