package postest2;

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
		SimpleXmlRegPopulator pop = new SimpleXmlRegPopulator("jpos.xml");
		pop.load();
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
