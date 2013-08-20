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
import jpos.Scale;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ScaleController extends CommonController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = new Scale();
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
				((Scale) service).setDeviceEnabled(true);
			} else {
				((Scale) service).setDeviceEnabled(false);
			}
			RequiredStateChecker.invokeThis(this, service);
		} catch (JposException je) {
			System.err.println("ScalePanel: CheckBoxListener: Jpos Exception" + je);
		}
	}

	@FXML
	public void handleOCE(ActionEvent e) {
		super.handleOCE(e);
		deviceEnabled.setSelected(true);
		handleDeviceEnable(e);
	}

	// Shows statistics of device if they are supported by the device
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			String ver = new Integer(((Scale) service).getDeviceServiceVersion()).toString();
			String msg = "Service Description: " + ((Scale) service).getDeviceServiceDescription();
			msg = msg + "\nService Version: v" + new Integer(ver.substring(0, 1)) + "."
					+ new Integer(ver.substring(1, 4)) + "." + new Integer(ver.substring(4, 7));
			ver = new Integer(((Scale) service).getDeviceControlVersion()).toString();
			msg += "\n\nControl Description: " + ((Scale) service).getDeviceControlDescription();
			msg += "\nControl Version: v" + new Integer(ver.substring(0, 1)) + "."
					+ new Integer(ver.substring(1, 4)) + "." + new Integer(ver.substring(4, 7));
			msg += "\n\nPhysical Device Name: " + ((Scale) service).getPhysicalDeviceName();
			msg += "\nPhysical Device Description: " + ((Scale) service).getPhysicalDeviceDescription();

			msg += "\n\nProperties:\n------------------------";

			msg += "\nCapStatisticsReporting: " + (((Scale) service).getCapStatisticsReporting());

			msg += "\nCapUpdateFirmware: " + (((Scale) service).getCapUpdateFirmware());

			msg += "\nCapCompareFirmwareVersion: " + (((Scale) service).getCapCompareFirmwareVersion());

			msg += "\nCapPowerReporting: "
					+ (((Scale) service).getCapPowerReporting() == JposConst.JPOS_PR_ADVANCED ? "Advanced"
							: (((Scale) service).getCapPowerReporting() == JposConst.JPOS_PR_STANDARD ? "Standard"
									: "None"));

			msg = msg + "\nCapDisplay: " + ((Scale) service).getCapDisplay();
			msg = msg + "\nCapDisplayText: " + ((Scale) service).getCapDisplayText();
			msg = msg + "\nCapPriceCalculating: " + ((Scale) service).getCapPriceCalculating();
			msg = msg + "\nCapStatusUpdate: " + ((Scale) service).getCapStatusUpdate();
			msg = msg + "\nCapTareWeight: " + ((Scale) service).getCapTareWeight();
			msg = msg + "\nCapZeroScale: " + ((Scale) service).getCapZeroScale();

			JOptionPane.showMessageDialog(null, msg, "Info", JOptionPane.INFORMATION_MESSAGE);

		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, "Exception in Info\nException: " + jpe.getMessage(),
					"Exception", JOptionPane.ERROR_MESSAGE);
			System.err.println("Jpos exception " + jpe);
		}
	}

	// Shows statistics of device if they are supported by the device
	@FXML
	public void handleStatistics(ActionEvent e) {
		String[] stats = new String[] { "", "U_", "M_" };
		try {
			((Scale) service).retrieveStatistics(stats);
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