package postest2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.JposConst;
import jpos.JposException;
import jpos.SignatureCapture;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SignatureCaptureController extends CommonController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public Pane functionPane;

	@FXML
	public TextField beginCapture_formName;

	@FXML
	public ComboBox<Boolean> realTimeDataEnabled;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = new SignatureCapture();
		RequiredStateChecker.invokeThis(this, service);
		setUpLogicalNameComboBox("SignatureCapture");
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((SignatureCapture) service).setDeviceEnabled(true);
				setUpComboBoxes();
			} else {
				((SignatureCapture) service).setDeviceEnabled(false);
			}
			RequiredStateChecker.invokeThis(this, service);
		} catch (JposException je) {
			System.err.println("SignatureCapturePanel: CheckBoxListener: Jpos Exception" + je);
		}
	}

	@Override
	@FXML
	public void handleOCE(ActionEvent e) {
		super.handleOCE(e);
		handleDeviceEnable(e);
	}

	/**
	 * Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			String ver = new Integer(((SignatureCapture) service).getDeviceServiceVersion()).toString();
			String msg = "Service Description: " + ((SignatureCapture) service).getDeviceServiceDescription();
			msg = msg + "\nService Version: v" + new Integer(ver.substring(0, 1)) + "."
					+ new Integer(ver.substring(1, 4)) + "." + new Integer(ver.substring(4, 7));
			ver = new Integer(((SignatureCapture) service).getDeviceControlVersion()).toString();
			msg += "\n\nControl Description: " + ((SignatureCapture) service).getDeviceControlDescription();
			msg += "\nControl Version: v" + new Integer(ver.substring(0, 1)) + "." + new Integer(ver.substring(1, 4))
					+ "." + new Integer(ver.substring(4, 7));
			msg += "\n\nPhysical Device Name: " + ((SignatureCapture) service).getPhysicalDeviceName();
			msg += "\nPhysical Device Description: " + ((SignatureCapture) service).getPhysicalDeviceDescription();

			msg += "\n\nProperties:\n------------------------";

			msg += "\nCapStatisticsReporting: " + (((SignatureCapture) service).getCapStatisticsReporting());

			msg += "\nCapUpdateFirmware: " + (((SignatureCapture) service).getCapUpdateFirmware());

			msg += "\nCapCompareFirmwareVersion: " + (((SignatureCapture) service).getCapCompareFirmwareVersion());

			msg += "\nCapPowerReporting: "
					+ (((SignatureCapture) service).getCapPowerReporting() == JposConst.JPOS_PR_ADVANCED ? "Advanced"
							: (((SignatureCapture) service).getCapPowerReporting() == JposConst.JPOS_PR_STANDARD ? "Standard"
									: "None"));

			msg = msg + "\nCapDisplay: " + ((SignatureCapture) service).getCapDisplay();
			msg = msg + "\nCapRealTimeData: " + ((SignatureCapture) service).getCapRealTimeData();
			msg = msg + "\nCapUserTerminated: " + ((SignatureCapture) service).getCapUserTerminated();

			JOptionPane.showMessageDialog(null, msg, "Info", JOptionPane.INFORMATION_MESSAGE);

		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, "Exception in Info\nException: " + jpe.getMessage(), "Exception",
					JOptionPane.ERROR_MESSAGE);
			System.err.println("Jpos exception " + jpe);
		}
	}

	/**
	 * Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleStatistics(ActionEvent e) {
		String[] stats = new String[] { "", "U_", "M_" };
		try {
			((SignatureCapture) service).retrieveStatistics(stats);
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

	@FXML
	public void handleSetRealTimeDataEnabled(ActionEvent e) {
		try {
			((SignatureCapture) service).setRealTimeDataEnabled(realTimeDataEnabled.getSelectionModel()
					.getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleBeginCapture(ActionEvent e) {
		if (beginCapture_formName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Parameter is not specified!");
		} else {
			try {
				((SignatureCapture) service).beginCapture(beginCapture_formName.getText());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleEndCapture(ActionEvent e) {
		try {
			((SignatureCapture) service).endCapture();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	/*
	 * Set up ComboBoxes
	 */

	private void setUpRealTimeDataEnabled() {
		realTimeDataEnabled.getItems().clear();
		realTimeDataEnabled.getItems().add(true);
		realTimeDataEnabled.getItems().add(false);
		realTimeDataEnabled.setValue(true);
	}

	private void setUpComboBoxes() {
		setUpRealTimeDataEnabled();
	}
}
