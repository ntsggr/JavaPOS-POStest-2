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
import jpos.JposConst;
import jpos.JposException;
import jpos.PointCardRW;

public class PointCardRWController implements Initializable {

	@FXML
	private ComboBox<String> logicalName;
	private PointCardRW pointCardRW;
	private static String statistics = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		pointCardRW = new PointCardRW();
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	// Shows information of device
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			String ver = new Integer(pointCardRW.getDeviceServiceVersion()).toString();
			String msg = "Service Description: " + pointCardRW.getDeviceServiceDescription();
			msg = msg + "\nService Version: v" + new Integer(ver.substring(0, 1)) + "."
					+ new Integer(ver.substring(1, 4)) + "." + new Integer(ver.substring(4, 7));
			ver = new Integer(pointCardRW.getDeviceControlVersion()).toString();
			msg += "\n\nControl Description: " + pointCardRW.getDeviceControlDescription();
			msg += "\nControl Version: v" + new Integer(ver.substring(0, 1)) + "."
					+ new Integer(ver.substring(1, 4)) + "." + new Integer(ver.substring(4, 7));
			msg += "\n\nPhysical Device Name: " + pointCardRW.getPhysicalDeviceName();
			msg += "\nPhysical Device Description: " + pointCardRW.getPhysicalDeviceDescription();

			msg += "\n\nProperties:\n------------------------";

			msg += "\nCapStatisticsReporting: " + (pointCardRW.getCapStatisticsReporting());

			msg += "\nCapUpdateFirmware: " + (pointCardRW.getCapUpdateFirmware());

			msg += "\nCapCompareFirmwareVersion: " + (pointCardRW.getCapCompareFirmwareVersion());

			msg += "\nCapPowerReporting: "
					+ (pointCardRW.getCapPowerReporting() == JposConst.JPOS_PR_ADVANCED ? "Advanced" : (pointCardRW
							.getCapPowerReporting() == JposConst.JPOS_PR_STANDARD ? "Standard" : "None"));

			msg = msg + "\nCapCardEntranceSensor: " + pointCardRW.getCapCardEntranceSensor();
			msg = msg + "\nCapCharacterSet: " + pointCardRW.getCapCharacterSet();
			msg = msg + "\nCapTracksToRead: " + pointCardRW.getCapTracksToRead();
			msg = msg + "\nCapTracksToWrite: " + pointCardRW.getCapTracksToWrite();
			msg = msg + "\nCapBold: " + pointCardRW.getCapBold();
			msg = msg + "\nCapCleanCard: " + pointCardRW.getCapCleanCard();
			msg = msg + "\nCapClearPrint: " + pointCardRW.getCapClearPrint();
			msg = msg + "\nCapDhigh: " + pointCardRW.getCapDhigh();
			msg = msg + "\nCapDwide: " + pointCardRW.getCapDwide();
			msg = msg + "\nCapDwideDhigh: " + pointCardRW.getCapDwideDhigh();
			msg = msg + "\nCapItalic: " + pointCardRW.getCapItalic();
			msg = msg + "\nCapLeft90: " + pointCardRW.getCapLeft90();
			msg = msg + "\nCapMapCharacterSet: " + pointCardRW.getCapMapCharacterSet();
			msg = msg + "\nCapPrint: " + pointCardRW.getCapPrint();
			msg = msg + "\nCapPrintMode: " + pointCardRW.getCapPrintMode();
			msg = msg + "\nCapRight90: " + pointCardRW.getCapRight90();
			msg = msg + "\nCapRotate180: " + pointCardRW.getCapRotate180();


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
			pointCardRW.retrieveStatistics(stats);
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
			FirmwareUpdateDlg dlg = new FirmwareUpdateDlg(pointCardRW);
			dlg.setVisible(true);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Exception: " + e2.getMessage(), "Failed",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}