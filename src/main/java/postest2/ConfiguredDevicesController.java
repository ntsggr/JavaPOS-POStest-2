package postest2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import jpos.config.JposEntry;
import jpos.config.simple.xml.SimpleXmlRegPopulator;

public class ConfiguredDevicesController implements Initializable {

	@FXML
	private TableView<Device> tableConfiguredDevices;
	@FXML
	private TableColumn<Device, String> category;
	@FXML
	private TableColumn<Device, String> logicalName;
	@FXML
	private TableColumn<Device, String> vendor;
	@FXML
	private TableColumn<Device, String> productName;

	@FXML
	private TextField selectJposPath;

	// List for the ConfiguredDevices Page
	private ObservableList<Device> devicesList;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpLists("jpos.xml");
		setUpTable();
	}
	
	
	@FXML
	public void handleBrowseJposXml(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Jpos.xml");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			selectJposPath.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleSetJposXml(ActionEvent e) {
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
			writer.print(this.selectJposPath.getText());
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		setUpLists(this.selectJposPath.getText());
		setUpTable();
	}
	

	/**
	 * Sets the DevicesList and the LogicalNamesList
	 */
	private void setUpLists(String pathToJposXml) {
		SimpleXmlRegPopulator pop = new SimpleXmlRegPopulator(pathToJposXml);
		pop.load(pathToJposXml);
		@SuppressWarnings("unchecked")
		Enumeration<JposEntry> enu = pop.getEntries();

		ArrayList<Device> devices = new ArrayList<Device>();

		while (enu.hasMoreElements()) {
			JposEntry je = enu.nextElement();

			String devCat = je.getPropertyValue(JposEntry.DEVICE_CATEGORY_PROP_NAME).toString();
			String devLogName = je.getLogicalName();
			String devVendor = je.getPropertyValue(JposEntry.VENDOR_NAME_PROP_NAME).toString();
			String devProductName = je.getPropertyValue(JposEntry.PRODUCT_NAME_PROP_NAME).toString();

			Device device = new Device(devCat, devLogName, devVendor, devProductName);
			devices.add(device);
		}

		devicesList = FXCollections.observableList(devices);
	}

	private void setUpTable() {
		category.setCellValueFactory(new PropertyValueFactory<Device, String>("category"));
		logicalName.setCellValueFactory(new PropertyValueFactory<Device, String>("logicalName"));
		vendor.setCellValueFactory(new PropertyValueFactory<Device, String>("vendor"));
		productName.setCellValueFactory(new PropertyValueFactory<Device, String>("productName"));

		tableConfiguredDevices.setItems(devicesList);
	}

}
