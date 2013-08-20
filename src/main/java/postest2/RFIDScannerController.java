package postest2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.JposConst;
import jpos.JposException;
import jpos.RFIDScanner;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class RFIDScannerController extends CommonController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = new RFIDScanner();
		// RequiredStateChecker.invokeThis(this, service);
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((RFIDScanner) service).setDeviceEnabled(true);
			} else {
				((RFIDScanner) service).setDeviceEnabled(false);
			}
			RequiredStateChecker.invokeThis(this, service);
		} catch (JposException je) {
			System.err.println("RFIDScannerPanel: CheckBoxListener: Jpos Exception" + je);
		}
	}

	@Override
	@FXML
	public void handleOCE(ActionEvent e) {
		super.handleOCE(e);
		deviceEnabled.setSelected(true);
		handleDeviceEnable(e);
	}

	// Shows statistics of device if they are supported by the device
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			String ver = new Integer(((RFIDScanner) service).getDeviceServiceVersion()).toString();
			String msg = "Service Description: " + ((RFIDScanner) service).getDeviceServiceDescription();
			msg = msg + "\nService Version: v" + new Integer(ver.substring(0, 1)) + "."
					+ new Integer(ver.substring(1, 4)) + "." + new Integer(ver.substring(4, 7));
			ver = new Integer(((RFIDScanner) service).getDeviceControlVersion()).toString();
			msg += "\n\nControl Description: " + ((RFIDScanner) service).getDeviceControlDescription();
			msg += "\nControl Version: v" + new Integer(ver.substring(0, 1)) + "."
					+ new Integer(ver.substring(1, 4)) + "." + new Integer(ver.substring(4, 7));
			msg += "\n\nPhysical Device Name: " + ((RFIDScanner) service).getPhysicalDeviceName();
			msg += "\nPhysical Device Description: " + ((RFIDScanner) service).getPhysicalDeviceDescription();

			msg += "\n\nProperties:\n------------------------";

			msg += "\nCapStatisticsReporting: " + (((RFIDScanner) service).getCapStatisticsReporting());

			msg += "\nCapUpdateFirmware: " + (((RFIDScanner) service).getCapUpdateFirmware());

			msg += "\nCapCompareFirmwareVersion: " + (((RFIDScanner) service).getCapCompareFirmwareVersion());

			msg += "\nCapPowerReporting: "
					+ (((RFIDScanner) service).getCapPowerReporting() == JposConst.JPOS_PR_ADVANCED ? "Advanced"
							: (((RFIDScanner) service).getCapPowerReporting() == JposConst.JPOS_PR_STANDARD ? "Standard"
									: "None"));

			msg = msg + "\nCapMultipleProtocols: " + ((RFIDScanner) service).getCapMultipleProtocols();
			msg = msg + "\nCapWriteTag: " + ((RFIDScanner) service).getCapWriteTag();
			msg = msg + "\nCapContinuousRead: " + ((RFIDScanner) service).getCapContinuousRead();
			msg = msg + "\nCapDisableTag: " + ((RFIDScanner) service).getCapDisableTag();
			msg = msg + "\nCapLockTag: " + ((RFIDScanner) service).getCapLockTag();
			msg = msg + "\nCapReadTimer: " + ((RFIDScanner) service).getCapReadTimer();
			msg = msg + "\nCapRealTimeData: " + ((RFIDScanner) service).getCapRealTimeData();

			JOptionPane.showMessageDialog(null, msg, "Info", JOptionPane.INFORMATION_MESSAGE);

		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, "Exception in Info\nException: " + jpe.getMessage(),
					"Exception", JOptionPane.ERROR_MESSAGE);
			System.err.println("Jpos exception " + jpe);
		}
	}

	// Shows statistics of device if they are supported by the device
	@Override
	@FXML
	public void handleStatistics(ActionEvent e) {
		String[] stats = new String[] { "", "U_", "M_" };
		try {
			((RFIDScanner) service).retrieveStatistics(stats);
			DOMParser parser = new DOMParser();
			parser.parse(new InputSource(new java.io.StringReader(stats[1])));

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new ByteArrayInputStream(stats[1].getBytes()));

			printStatistics(doc.getDocumentElement(), "");

			JOptionPane.showMessageDialog(null, statistics, "Statistics", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException saxe) {
			saxe.printStackTrace();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (JposException jpe) {
			jpe.printStackTrace();
			JOptionPane.showMessageDialog(null, "Statistics are not supported!", "Statistics",
					JOptionPane.ERROR_MESSAGE);
		}

		statistics = "";
	}

}