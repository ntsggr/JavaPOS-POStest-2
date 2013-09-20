package postest2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jpos.config.JposEntry;
import jpos.config.simple.xml.SimpleXmlRegPopulator;

public class LogicalNameGetter {

	static ObservableList<Device> devicesList;

	public static ObservableList<String> getLogicalNamesByCategory(String category) {
		setUpList();

		ArrayList<String> logicalNames = new ArrayList<String>();
		for (Device d : devicesList) {
			if (d.getCategory().equals(category)) {
				logicalNames.add(d.getLogicalName());
			}
		}
		return FXCollections.observableArrayList(logicalNames);
	}

	private static void setUpList() {
		String jpos = null;
		
		File f = new File("pathToJposEntries.dat");
		if(f.exists()){
			byte[] encoded;
			try {
				encoded = Files.readAllBytes(Paths.get("pathToJposEntries.dat"));
				if(encoded.length != 0){
					jpos = StandardCharsets.UTF_8.decode(ByteBuffer.wrap(encoded)).toString();
				} else {
					jpos = "jpos.xml";
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			jpos = "jpos.xml";
		}
		
		
		SimpleXmlRegPopulator pop = new SimpleXmlRegPopulator(jpos);
		pop.load(jpos);
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
}
