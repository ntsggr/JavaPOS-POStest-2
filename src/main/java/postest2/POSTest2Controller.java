package postest2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.util.Callback;

public class POSTest2Controller implements Initializable {

	// Device panels
	private Parent About = null;
	private Parent ConfiguredDevices = null;
	private Parent Belt = null;
	private Parent BillAcceptor = null;
	private Parent BillDispenser = null;
	private Parent Biometrics = null;
	private Parent BumpBar = null;
	private Parent CashChanger = null;
	private Parent CashDrawer = null;
	private Parent CAT = null;
	private Parent CheckScanner = null;
	private Parent CoinAcceptor = null;
	private Parent CoinDispenser = null;
	private Parent ElectronicJournal = null;
	private Parent ElectronicValueRW = null;
	private Parent FiscalPrinter = null;
	private Parent Gate = null;
	private Parent HardTotals = null;
	private Parent ImageScanner = null;
	private Parent ItemDispenser = null;
	private Parent Keylock = null;
	private Parent Lights = null;
	private Parent LineDisplay = null;
	private Parent MICR = null;
	private Parent MotionSensor = null;
	private Parent MSR = null;
	private Parent PINPad = null;
	private Parent PointCardRW = null;
	private Parent POSKeyboard = null;
	private Parent POSPower = null;
	private Parent POSPrinter = null;
	private Parent RemoteOrderDisplay = null;
	private Parent RFIDScanner = null;
	private Parent Scale = null;
	private Parent Scanner = null;
	private Parent SignatureCapture = null;
	private Parent SmartCardRW = null;
	private Parent ToneIndicator = null;

	@FXML
	// contains and shows the available devices
	private ListView<String> listAllDevices;

	@FXML
	// using the "favoriteDevices" it contains and shows the favorite devices
	transient private ListView<String> listFavorites;

	@FXML
	// button to select All Devices list
	private ToggleButton toggleButtonAllDevices;

	@FXML
	// button to select Favorite Devices list
	private ToggleButton toggleButtonFavorites;

	@FXML
	// the main window is divided into two panels. The right panel holds the panel of each clicked device
	private AnchorPane anchorPaneRight;

	@FXML
	// contains and shows the available devices
	private ObservableList<String> favoriteDevices;

	public List<String> storeFavorites;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadDevicePanels();
		favoriteDevices = FXCollections.observableArrayList();

		File f = new File("listOfFavoriteDevices.dat");
		if (f.exists()) {
			storeFavorites = retrieveFavorites();
		} else {
			storeFavorites = new ArrayList<String>();
		}

		// group for the toggles buttons
		final ToggleGroup toggleGroup = new ToggleGroup();
		// set user data to compare which togglebutton is selected
		toggleButtonAllDevices.setUserData("all");
		toggleButtonAllDevices.setToggleGroup(toggleGroup);
		// set user data to compare which togglebutton is selected
		toggleButtonFavorites.setUserData("favorites");
		toggleButtonFavorites.setToggleGroup(toggleGroup);
		toggleButtonAllDevices.setSelected(true);
		setPanel("About");

		// The setCellFactory method redefines the implementation of the list cell
		listAllDevices.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new XCell();
			}
		});

		// Show the respective device panel for each selected item from the list "All devices"
		listAllDevices.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
				setPanel(new_val);
			}
		});

		// Show the respective device panel for each selected item from the list "Favorites"
		listFavorites.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
				if (new_val != null)
					setPanel(new_val);
				else
					setPanel(old_val);
			}
		});

		// Show "All devices" or "Favorites"
		toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle new_toggle) {
				if (new_toggle != null) {
					if (new_toggle.getUserData().equals("all")) {
						listAllDevices.setVisible(true);
						listFavorites.setVisible(false);
					} else {
						listAllDevices.setVisible(false);
						listFavorites.setVisible(true);
					}
				}
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
						saveFavorites();
					} else {
						favoriteDevices.add(lastItem);
						listFavorites.setItems(favoriteDevices);
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
	protected void setPanel(String panel) {
		if (panel.equals("About")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(About);
		} else if (panel.equals("ConfiguredDevices")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(ConfiguredDevices);
		} else if (panel.equals("Belt")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(Belt);
		} else if (panel.equals("BillAcceptor")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(BillAcceptor);
		} else if (panel.equals("BillDispenser")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(BillDispenser);
		} else if (panel.equals("Biometrics")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(Biometrics);
		} else if (panel.equals("BumpBar")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(BumpBar);
		} else if (panel.equals("CashChanger")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(CashChanger);
		} else if (panel.equals("CashDrawer")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(CashDrawer);
		} else if (panel.equals("CAT")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(CAT);
		} else if (panel.equals("CheckScanner")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(CheckScanner);
		} else if (panel.equals("CoinAcceptor")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(CoinAcceptor);
		} else if (panel.equals("CoinDispenser")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(CoinDispenser);
		} else if (panel.equals("ElectronicJournal")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(ElectronicJournal);
		} else if (panel.equals("ElectronicValueRW")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(ElectronicValueRW);
		} else if (panel.equals("FiscalPrinter")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(FiscalPrinter);
		} else if (panel.equals("Gate")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(Gate);
		} else if (panel.equals("HardTotals")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(HardTotals);
		} else if (panel.equals("ImageScanner")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(ImageScanner);
		} else if (panel.equals("ItemDispenser")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(ItemDispenser);
		} else if (panel.equals("Keylock")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(Keylock);
		} else if (panel.equals("Lights")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(Lights);
		} else if (panel.equals("LineDisplay")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(LineDisplay);
		} else if (panel.equals("MICR")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(MICR);
		} else if (panel.equals("MotionSensor")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(MotionSensor);
		} else if (panel.equals("MSR")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(MSR);
		} else if (panel.equals("PINPad")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(PINPad);
		} else if (panel.equals("PointCardRW")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(PointCardRW);
		} else if (panel.equals("POSKeyboard")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(POSKeyboard);
		} else if (panel.equals("POSPower")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(POSPower);
		} else if (panel.equals("POSPrinter")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(POSPrinter);
		} else if (panel.equals("RemoteOrderDisplay")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(RemoteOrderDisplay);
		} else if (panel.equals("RFIDScanner")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(RFIDScanner);
		} else if (panel.equals("Scale")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(Scale);
		} else if (panel.equals("Scanner")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(Scanner);
		} else if (panel.equals("SignatureCapture")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(SignatureCapture);
		} else if (panel.equals("SmartCardRW")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(SmartCardRW);
		} else if (panel.equals("ToneIndicator")) {
			anchorPaneRight.getChildren().clear();
			anchorPaneRight.getChildren().addAll(ToneIndicator);
		}
	}

}
