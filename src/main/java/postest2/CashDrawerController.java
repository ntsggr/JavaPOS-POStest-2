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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import jpos.CashDrawer;
import jpos.CashDrawerConst;
import jpos.JposConst;
import jpos.JposException;
import jpos.LineDisplayConst;
import jpos.events.StatusUpdateEvent;
import jpos.events.StatusUpdateListener;
import jpos.profile.JposDevCats;

public class CashDrawerController implements Initializable, StatusUpdateListener {

	// Common
	@FXML
	private ComboBox<String> logicalName;
	@FXML
	private Button buttonOpen;
	@FXML
	private Button buttonClaim;
	@FXML
	private Button buttonRelease;
	@FXML
	private Button buttonInfo;
	@FXML
	private Button buttonStatistics;
	@FXML
	private Button buttonFirmware;
	@FXML
	private CheckBox deviceEnabled;
	@FXML
	private Label statusLabel;

	// CashDrawer
	@FXML
	private TextArea textAreaActionLog;
	@FXML
	private Button buttonOpenCash;
	@FXML
	private Button buttonGetDrawer;
	@FXML
	private Button buttonWaitForDrawer;

	private CashDrawer cashDrawer;
	private String defaultLogicalName = "defaultCashDrawer";
	private static String statistics = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpLogicalNameComboBox();
		cashDrawer = new CashDrawer();
		cashDrawer.addStatusUpdateListener(this);
	}

	private void setUpLogicalNameComboBox() {
		logicalName.setItems(LogicalNameGetter.getLogicalNamesByCategory(JposDevCats.CASHDRAWER_DEVCAT
				.toString()));
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleOpen(ActionEvent e) {
		try {
			if (logicalName.getValue() != null && !logicalName.getValue().isEmpty()) {
				cashDrawer.open(logicalName.getValue());
				buttonClaim.setDisable(false);
				int version = cashDrawer.getDeviceServiceVersion();
				// statusLabel.setText("JPOS_S_IDLE");
			} else {
				JOptionPane.showMessageDialog(null, "Choose a device!", "Logical name is empty",
						JOptionPane.WARNING_MESSAGE);
			}

		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to claim \"" + "CashDrawer" + "\"\nException: " + je.getMessage(), "Failed",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Requests exclusive access to the device
	@FXML
	public void handleClaim(ActionEvent e) {
		try {
			cashDrawer.claim(0);
			deviceEnabled.setDisable(false);
			buttonRelease.setDisable(false);
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to claim \"" + "CashDrawer" + "\"\nException: " + je.getMessage(), "Failed",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				cashDrawer.setDeviceEnabled(true);
				buttonOpenCash.setDisable(false);
				buttonGetDrawer.setDisable(false);
				buttonWaitForDrawer.setDisable(false);
			} else {
				cashDrawer.setDeviceEnabled(false);
				buttonOpenCash.setDisable(true);
				buttonGetDrawer.setDisable(true);
				buttonWaitForDrawer.setDisable(true);
			}
		} catch (JposException je) {
			System.err.println("CashDrawerPanel: CheckBoxListener: Jpos Exception" + je);
		}
	}

	// Releases exclusive access to the device. The device is also disabled.
	@FXML
	public void handleRelease(ActionEvent e) {
		try {
			cashDrawer.release();
			if (deviceEnabled.isSelected()) {
				deviceEnabled.setSelected(false);
				buttonOpenCash.setDisable(true);
				buttonGetDrawer.setDisable(true);
				buttonWaitForDrawer.setDisable(true);
			}
			deviceEnabled.setDisable(true);
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to release \"" + logicalName + "\"\nException: " + je.getMessage(), "Failed",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Releases the device and its resources. Also the device is released.
	@FXML
	public void handleClose(ActionEvent e) {
		try {
			cashDrawer.close();
			if (!deviceEnabled.isDisable()) {
				deviceEnabled.setSelected(false);
				buttonOpenCash.setDisable(true);
				buttonGetDrawer.setDisable(true);
				buttonWaitForDrawer.setDisable(true);
			}
			buttonClaim.setDisable(true);
			deviceEnabled.setDisable(true);
			buttonRelease.setDisable(true);
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to close \"" + logicalName + "\"\nException: " + je.getMessage(), "Failed",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			String ver = new Integer(cashDrawer.getDeviceServiceVersion()).toString();
			String msg = "Service Description: " + cashDrawer.getDeviceServiceDescription();
			msg = msg + "\nService Version: v" + new Integer(ver.substring(0, 1)) + "."
					+ new Integer(ver.substring(1, 4)) + "." + new Integer(ver.substring(4, 7));
			ver = new Integer(cashDrawer.getDeviceControlVersion()).toString();
			msg += "\n\nControl Description: " + cashDrawer.getDeviceControlDescription();
			msg += "\nControl Version: v" + new Integer(ver.substring(0, 1)) + "."
					+ new Integer(ver.substring(1, 4)) + "." + new Integer(ver.substring(4, 7));
			msg += "\n\nPhysical Device Name: " + cashDrawer.getPhysicalDeviceName();
			msg += "\nPhysical Device Description: " + cashDrawer.getPhysicalDeviceDescription();

			msg += "\n\nProperties:\n------------------------";

			msg += "\nCapStatisticsReporting: " + (cashDrawer.getCapStatisticsReporting());

			msg += "\nCapUpdateFirmware: " + (cashDrawer.getCapUpdateFirmware());

			msg += "\nCapCompareFirmwareVersion: " + (cashDrawer.getCapCompareFirmwareVersion());

			msg += "\nCapPowerReporting: "
					+ (cashDrawer.getCapPowerReporting() == JposConst.JPOS_PR_ADVANCED ? "Advanced"
							: (cashDrawer.getCapPowerReporting() == JposConst.JPOS_PR_STANDARD ? "Standard"
									: "None"));
			
			msg = msg + "\nCapStatus: " + cashDrawer.getCapStatus();
			msg = msg + "\nCapStatusMultiDrawerDetect: " + cashDrawer.getCapStatusMultiDrawerDetect();

			JOptionPane.showMessageDialog(null, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, "Exception in Info\nException: " + jpe.getMessage(),
					"Exception", JOptionPane.ERROR_MESSAGE);
			System.err.println("Jpos exception " + jpe);
		}
	}

	@FXML
	public void handleStatistics(ActionEvent e) {
		String[] stats = new String[] { "", "U_", "M_" };
		try {
			cashDrawer.retrieveStatistics(stats);
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
			FirmwareUpdateDlg dlg = new FirmwareUpdateDlg(cashDrawer);
			dlg.setVisible(true);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Exception: " + e2.getMessage(), "Failed",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleOpenCash(ActionEvent e) {
		try {
			cashDrawer.openDrawer();
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, "Exception in openDrawer: " + je.getMessage(), "Exception",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleGetDrawer(ActionEvent e) {
		try {
			if (cashDrawer.getDrawerOpened()) {
				textAreaActionLog.appendText("Cash drawer is open.\n");
			} else {
				textAreaActionLog.appendText("Cash drawer is closed.\n");
			}
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, "Exception in getDrawerOpened: " + je.getMessage(),
					"Exception", JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleWaitForDrawer(ActionEvent e) {
		try {
			cashDrawer.waitForDrawerClose(100, 500, 100, 200);
			textAreaActionLog.appendText("Cash drawer is closed.\n");
		} catch (JposException je) {
			textAreaActionLog.appendText("Jpos exception " + je + "\n");
			JOptionPane.showMessageDialog(null, "Exception in waitForDrawerClose: " + je.getMessage(),
					"Exception", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void statusUpdateOccurred(StatusUpdateEvent sue) {
		String msg = "Status Update Event: ";
		statusLabel.setText("" + sue.getStatus());
		switch (sue.getStatus()) {
		case CashDrawerConst.CASH_SUE_DRAWERCLOSED:
			msg += "Drawer Closed\n";
			break;
		case CashDrawerConst.CASH_SUE_DRAWEROPEN:
			msg += "Drawer Opened\n";
			break;
		default:
			msg += "Unknown Status: " + Integer.toString(sue.getStatus()) + "\n";
			break;
		}
	}

}