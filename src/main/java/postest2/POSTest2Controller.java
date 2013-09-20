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
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
	public ListView<String> listFavorites;

	// the main window is divided into two panels. The right panel holds the
	// panel of each clicked device
	@FXML
	public AnchorPane anchorPaneRight;

	// contains and shows the available devices
	@FXML
	public ObservableList<String> favoriteDevices;

	// list is used to save favorite devices when the application terminates
	public List<String> storeFavorites;

	// keeps the current name of a selected device from any list
	private static String selectedItem = "";

	private String jposPath = "jpos.xml";

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		clearPathFile();
		loadDevicePanels();
		favoriteDevices = FXCollections.observableArrayList();

		File f = new File("listOfFavoriteDevices.dat");
		if (f.exists()) {
			storeFavorites = retrieveFavorites();
			Iterator<String> it = storeFavorites.iterator();
			while (it.hasNext()) {
				favoriteDevices.add(it.next());
				listFavorites.setPrefHeight(listFavorites.getPrefHeight() + 36.0);
			}
			listFavorites.setItems(favoriteDevices);
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

		// if and only if a double click is detected then add the selected
		// device to the favorites
		listAllDevices.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				if (me.getButton().equals(MouseButton.PRIMARY)) {
					if (me.getClickCount() == 2) {
						if (!favoriteDevices.contains(selectedItem)) {
							favoriteDevices.add(selectedItem);
							Collections.sort(favoriteDevices, ALPHABETICAL_ORDER);
							listFavorites.setItems(favoriteDevices);
							storeFavorites.add(selectedItem);
							Collections.sort(storeFavorites, ALPHABETICAL_ORDER);
							listFavorites.setPrefHeight(listFavorites.getPrefHeight() + 36.0);
							saveFavorites();
						}
					}
				}
			}
		});

		// if and only if a double click is detected then remove the selected
		// device from the favorites
		listFavorites.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				if (me.getButton().equals(MouseButton.PRIMARY)) {
					if (me.getClickCount() == 2) {
						if (favoriteDevices.contains(selectedItem)) {
							favoriteDevices.remove(selectedItem);
							listFavorites.setItems(favoriteDevices);
							storeFavorites.remove(selectedItem);
							listFavorites.setPrefHeight(listFavorites.getPrefHeight() - 36.0);
							saveFavorites();
						}
					}
				}
			}
		});

		// Show the respective device panel for each selected item from the list
		// "All devices"
		listAllDevices.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
				if (new_val != null) {
					selectedItem = new_val;
					if(!compareJposPath()){
						loadDevicePanels();
					}
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
					selectedItem = new_val;
					setPanel(new_val);
				}
				listFavorites.getSelectionModel().clearSelection();
			}
		});

	}

	// method to sort lists alphabetically
	private static Comparator<String> ALPHABETICAL_ORDER = new Comparator<String>() {
		public int compare(String str1, String str2) {
			int res = String.CASE_INSENSITIVE_ORDER.compare(str1, str2);
			return (res != 0) ? res : str1.compareTo(str2);
		}
	};

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

	private void clearPathFile() {
		File f = new File("pathToJposEntries.dat");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		// Clear file and write new path!
		try (PrintWriter writer = new PrintWriter(f)) {
			writer.print("");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	private boolean compareJposPath() {
		File f = new File("pathToJposEntries.dat");
		String jpos;
		if (f.exists()) {
			byte[] encoded;
			try {
				encoded = Files.readAllBytes(Paths.get("pathToJposEntries.dat"));
				if (encoded.length != 0) {
					jpos = StandardCharsets.UTF_8.decode(ByteBuffer.wrap(encoded)).toString();
					if (!jpos.equals(jposPath)) {
						jposPath = jpos.substring(0);
						return false;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

}
