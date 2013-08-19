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
import javafx.scene.text.Text;
import jpos.CashDrawerConst;
import jpos.FiscalPrinter;
import jpos.FiscalPrinterConst;
import jpos.JposConst;
import jpos.JposException;
import jpos.events.StatusUpdateEvent;
import jpos.events.StatusUpdateListener;
import jpos.profile.JposDevCats;

public class FiscalPrinterController implements Initializable, StatusUpdateListener {

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
	private Text statusLabel;
	@FXML
	private CheckBox asyncMode;
	@FXML
	private CheckBox freezeEvents;
	@FXML
	private CheckBox cbVoid;
	@FXML
	private ComboBox<String> adjustmentType;

	private FiscalPrinter fiscalPrinter;
	private static String statistics = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fiscalPrinter = new FiscalPrinter();
		
		setUpLogicalNameComboBox();
	}
	
	private void setUpLogicalNameComboBox() {
		logicalName.setItems(LogicalNameGetter.getLogicalNamesByCategory(JposDevCats.POSPRINTER_DEVCAT
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
				fiscalPrinter.open(logicalName.getValue());
				buttonClaim.setDisable(false);
				System.out.println(fiscalPrinter.getState());
			} else {
				JOptionPane.showMessageDialog(null, "Choose a device!", "Logical name is empty",
						JOptionPane.WARNING_MESSAGE);
			}

		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to claim \"" + logicalName.getSelectionModel().getSelectedItem()
							+ "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Requests exclusive access to the device
	@FXML
	public void handleClaim(ActionEvent e) {
		try {
			fiscalPrinter.claim(0);
			deviceEnabled.setDisable(false);
			buttonRelease.setDisable(false);

		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to claim \"" + logicalName.getSelectionModel().getSelectedItem()
							+ "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				fiscalPrinter.setDeviceEnabled(true);
			} else {
				fiscalPrinter.setDeviceEnabled(false);
			}
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
		}
	}

	// Releases exclusive access to the device. The device is also disabled.
	@FXML
	public void handleRelease(ActionEvent e) {
		try {
			fiscalPrinter.release();
			fiscalPrinter.printRecItemAdjustmentVoid(adjustmentType, description, amount, vatInfo)
			fiscalPrinter.printRecItemAdjustment(adjustmentType, description, amount, vatInfo)
			fiscalPrinter.printRecItem(description, price, quantity, vatInfo, unitPrice, unitName)
			if (deviceEnabled.isSelected()) {
				deviceEnabled.setSelected(false);
			}
			deviceEnabled.setDisable(true);
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to release \"" + logicalName.getSelectionModel().getSelectedItem()
							+ "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Releases the device and its resources. Also the device is released.
	@FXML
	public void handleClose(ActionEvent e) {
		try {
			fiscalPrinter.close();
			if (!deviceEnabled.isDisable()) {
				deviceEnabled.setSelected(false);
			}
			buttonClaim.setDisable(true);
			deviceEnabled.setDisable(true);
			buttonRelease.setDisable(true);
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to close \"" + logicalName.getSelectionModel().getSelectedItem()
							+ "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@FXML
	public void handleAsyncMode(ActionEvent e) {
		try {
			fiscalPrinter.setAsyncMode(asyncMode.selectedProperty().getValue());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}
	
	@FXML
	public void handleFreezeEvents(ActionEvent e) {
		try {
			fiscalPrinter.setFreezeEvents(freezeEvents.selectedProperty().getValue());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	// Shows information of device
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			String ver = new Integer(fiscalPrinter.getDeviceServiceVersion()).toString();
			String msg = "Service Description: " + fiscalPrinter.getDeviceServiceDescription();
			msg = msg + "\nService Version: v" + new Integer(ver.substring(0, 1)) + "."
					+ new Integer(ver.substring(1, 4)) + "." + new Integer(ver.substring(4, 7));
			ver = new Integer(fiscalPrinter.getDeviceControlVersion()).toString();
			msg += "\n\nControl Description: " + fiscalPrinter.getDeviceControlDescription();
			msg += "\nControl Version: v" + new Integer(ver.substring(0, 1)) + "."
					+ new Integer(ver.substring(1, 4)) + "." + new Integer(ver.substring(4, 7));
			msg += "\n\nPhysical Device Name: " + fiscalPrinter.getPhysicalDeviceName();
			msg += "\nPhysical Device Description: " + fiscalPrinter.getPhysicalDeviceDescription();

			msg += "\n\nProperties:\n------------------------";

			msg += "\nCapStatisticsReporting: " + (fiscalPrinter.getCapStatisticsReporting());

			msg += "\nCapUpdateFirmware: " + (fiscalPrinter.getCapUpdateFirmware());

			msg += "\nCapCompareFirmwareVersion: " + (fiscalPrinter.getCapCompareFirmwareVersion());

			msg += "\nCapPowerReporting: "
					+ (fiscalPrinter.getCapPowerReporting() == JposConst.JPOS_PR_ADVANCED ? "Advanced" : (fiscalPrinter
							.getCapPowerReporting() == JposConst.JPOS_PR_STANDARD ? "Standard" : "None"));
			
			msg = msg + "\nCapAdditionalHeader: " + fiscalPrinter.getCapAdditionalHeader();
			msg = msg + "\nCapAdditionalLines: " + fiscalPrinter.getCapAdditionalLines();
			msg = msg + "\nCapAdditionalTrailer: " + fiscalPrinter.getCapAdditionalTrailer();
			msg = msg + "\nCapAmountAdjustment: " + fiscalPrinter.getCapAmountAdjustment();
			msg = msg + "\nCapAmountNotPaid: " + fiscalPrinter.getCapAmountNotPaid();
			msg = msg + "\nCapChangeDue: " + fiscalPrinter.getCapChangeDue();
			msg = msg + "\nCapCheckTotal: " + fiscalPrinter.getCapCheckTotal();
			msg = msg + "\nCapCoverSensor: " + fiscalPrinter.getCapCoverSensor();
			msg = msg + "\nCapDoubleWidth: " + fiscalPrinter.getCapDoubleWidth();
			msg = msg + "\nCaDuplicateReceipt: " + fiscalPrinter.getCapDuplicateReceipt();
			msg = msg + "\nCapEmptyReceiptIsVoidable: " + fiscalPrinter.getCapEmptyReceiptIsVoidable();
			msg = msg + "\nCapFiscalReceiptStation: " + fiscalPrinter.getCapFiscalReceiptStation();
			msg = msg + "\nCapFiscalReceiptType: " + fiscalPrinter.getCapFiscalReceiptType();
			msg = msg + "\nCapFixedOutput: " + fiscalPrinter.getCapFixedOutput();
			msg = msg + "\nCapHasVatTable: " + fiscalPrinter.getCapHasVatTable();
			msg = msg + "\nCapIndependentHeader: " + fiscalPrinter.getCapIndependentHeader();
			msg = msg + "\nCapItemList: " + fiscalPrinter.getCapItemList();
			msg = msg + "\nCapJrnEmptySensor: " + fiscalPrinter.getCapJrnEmptySensor();
			msg = msg + "\nCapJrnNearEndSensor: " + fiscalPrinter.getCapJrnNearEndSensor();
			msg = msg + "\nCapJrnPresen: " + fiscalPrinter.getCapJrnPresent();
			msg = msg + "\nCapMultiContractor: " + fiscalPrinter.getCapMultiContractor();
			msg = msg + "\nCapNonFiscalMode: " + fiscalPrinter.getCapNonFiscalMode();
			msg = msg + "\nCapOnlyVoidLastItem: " + fiscalPrinter.getCapOnlyVoidLastItem();
			msg = msg + "\nCapOrderAdjustmentFirst: " + fiscalPrinter.getCapOrderAdjustmentFirst();
			msg = msg + "\nCapPackageAdjustment: " + fiscalPrinter.getCapPackageAdjustment();
			msg = msg + "\nCapPercentAdjustment: " + fiscalPrinter.getCapPercentAdjustment();
			msg = msg + "\nCapPositiveAdjustment: " + fiscalPrinter.getCapPositiveAdjustment();
			msg = msg + "\nCapPostPreLine: " + fiscalPrinter.getCapPostPreLine();
			msg = msg + "\nCapRemainingFiscalMemory: " + fiscalPrinter.getCapRemainingFiscalMemory();
			msg = msg + "\nCaSetCurrency: " + fiscalPrinter.getCapSetCurrency();
			msg = msg + "\nCapSetPOSID: " + fiscalPrinter.getCapSetPOSID();
			msg = msg + "\nCapStoreFiscalID: " + fiscalPrinter.getCapSetStoreFiscalID();
			msg = msg + "\nCapTrainingMode: " + fiscalPrinter.getCapTrainingMode();
			msg = msg + "\nCapSetVatTable: " + fiscalPrinter.getCapSetVatTable();

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
			fiscalPrinter.retrieveStatistics(stats);
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
			FirmwareUpdateDlg dlg = new FirmwareUpdateDlg(fiscalPrinter);
			dlg.setVisible(true);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Exception: " + e2.getMessage(), "Failed",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void statusUpdateOccurred(StatusUpdateEvent sue) {
		String msg = "Status Update Event: ";
		statusLabel.setText("" + sue.getStatus());
	}

}
