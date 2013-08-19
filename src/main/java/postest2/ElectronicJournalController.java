package postest2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import jpos.ElectronicJournal;
import jpos.JposConst;
import jpos.JposException;

public class ElectronicJournalController implements Initializable {

	@FXML
	private ComboBox<String> logicalName;
	private ElectronicJournal electronicJournal;
	private static String statistics = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		electronicJournal = new ElectronicJournal();
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	// Shows information of device
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			String ver = new Integer(electronicJournal.getDeviceServiceVersion()).toString();
			String msg = "Service Description: " + electronicJournal.getDeviceServiceDescription();
			msg = msg + "\nService Version: v" + new Integer(ver.substring(0, 1)) + "."
					+ new Integer(ver.substring(1, 4)) + "." + new Integer(ver.substring(4, 7));
			ver = new Integer(electronicJournal.getDeviceControlVersion()).toString();
			msg += "\n\nControl Description: " + electronicJournal.getDeviceControlDescription();
			msg += "\nControl Version: v" + new Integer(ver.substring(0, 1)) + "."
					+ new Integer(ver.substring(1, 4)) + "." + new Integer(ver.substring(4, 7));
			msg += "\n\nPhysical Device Name: " + electronicJournal.getPhysicalDeviceName();
			msg += "\nPhysical Device Description: " + electronicJournal.getPhysicalDeviceDescription();

			msg += "\n\nProperties:\n------------------------";

			msg += "\nCapStatisticsReporting: " + (electronicJournal.getCapStatisticsReporting());

			msg += "\nCapUpdateFirmware: " + (electronicJournal.getCapUpdateFirmware());

			msg += "\nCapCompareFirmwareVersion: " + (electronicJournal.getCapCompareFirmwareVersion());

			msg += "\nCapPowerReporting: "
					+ (electronicJournal.getCapPowerReporting() == JposConst.JPOS_PR_ADVANCED ? "Advanced"
							: (electronicJournal.getCapPowerReporting() == JposConst.JPOS_PR_STANDARD ? "Standard"
									: "None"));
			
			msg = msg + "\nCapStation: " + electronicJournal.getCapStation();
			msg = msg + "\nCapAddMarker: " + electronicJournal.getCapAddMarker();
			msg = msg + "\nCapErasableMedium: " + electronicJournal.getCapErasableMedium();
			msg = msg + "\nCapInitializeMedium: " + electronicJournal.getCapInitializeMedium();
			msg = msg + "\nCapMediumIsAvailable: " + electronicJournal.getCapMediumIsAvailable();
			msg = msg + "\nCapPrintContent: " + electronicJournal.getCapPrintContent();
			msg = msg + "\nCapPrintContentFile: " + electronicJournal.getCapPrintContentFile();
			msg = msg + "\nCapRetrieveCurrentMarker: " + electronicJournal.getCapRetrieveCurrentMarker();
			msg = msg + "\nCapRetrieveMarker: " + electronicJournal.getCapRetrieveMarker();
			msg = msg + "\nCapRetrieveMarkerByDateTime: " + electronicJournal.getCapRetrieveMarkerByDateTime();
			msg = msg + "\nCapRetrieveMarkersDateTime: " + electronicJournal.getCapRetrieveMarkersDateTime();
			msg = msg + "\nCapStorageEnabled: " + electronicJournal.getCapStorageEnabled();
			msg = msg + "\nCapSuspendPrintContent: " + electronicJournal.getCapSuspendPrintContent();
			msg = msg + "\nCapSuspendQueryContent: " + electronicJournal.getCapSuspendQueryContent();
			msg = msg + "\nCapWaterMark: " + electronicJournal.getCapWaterMark();

			JOptionPane.showMessageDialog(null, msg, "Info", JOptionPane.INFORMATION_MESSAGE);

		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, "Exception in Info\nException: " + jpe.getMessage(),
					"Exception", JOptionPane.ERROR_MESSAGE);
			System.err.println("Jpos exception " + jpe);
		}
	}

	// Shows statistics of device
	@FXML
	public void handleStatistics(ActionEvent e) {
		String[] stats = new String[] { "", "U_", "M_" };
		try {
			electronicJournal.retrieveStatistics(stats);
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}

		try {
			DOMParser parser = new DOMParser();
			parser.parse(new InputSource(new java.io.StringReader(stats[1])));

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new ByteArrayInputStream(stats[1].getBytes()));

			printStatistics(doc.getDocumentElement(), "");

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException saxe) {
			saxe.printStackTrace();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, statistics, "Statistics", JOptionPane.INFORMATION_MESSAGE);
		statistics = "";
	}

	// Method to parse the String XML and print the data
	private static void printStatistics(Node e, String tab) {
		if (e.getNodeType() == Node.TEXT_NODE) {
			statistics += tab + e.getNodeValue() + "\n";
			return;
		}

		if (!(e.getNodeName().equals("Name") || e.getNodeName().equals("Value")
				|| e.getNodeName().equals("UPOSStat") || e.getNodeName().equals("Event")
				|| e.getNodeName().equals("Equipment") || e.getNodeName().equals("Parameter")))
			statistics += tab + e.getNodeName();

		if (e.getNodeValue() != null) {
			statistics += tab + " " + e.getNodeValue();
		}

		NodeList childs = e.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			printStatistics(childs.item(i), " ");
		}
	}
	
	@FXML
	public void handleFirmware(ActionEvent e) {
		try {
			FirmwareUpdateDlg dlg = new FirmwareUpdateDlg(electronicJournal);
			dlg.setVisible(true);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Exception: " + e2.getMessage(), "Failed",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
