package postest2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

import javax.swing.JOptionPane;

import jpos.BaseJposControl;
import jpos.JposConst;
import jpos.JposException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class CommonController implements Initializable {

	/* ************************************************************************
	 * ************************ Action Handler ********************************
	 * ************************************************************************
	 */

	// Common
	@FXML
	public ComboBox<String> logicalName;
	@FXML
	@RequiredState(JposState.CLAIMED)
	public CheckBox deviceEnabled;
	@FXML
	public Button buttonOpen;
	@FXML
	@RequiredState(JposState.OPENED)
	public Button buttonClaim;
	@FXML
	@RequiredState(JposState.OPENED)
	public Button buttonRelease;
	@FXML
	@RequiredState(JposState.OPENED)
	public Button buttonStatistics;
	@FXML
	@RequiredState(JposState.OPENED)
	public Button buttonClose;
	@FXML
	@RequiredState(JposState.OPENED)
	public Button buttonFirmware;
	@FXML
	@RequiredState(JposState.CLOSED)
	public Button buttonOCE;
	@FXML
	@RequiredState(JposState.ENABLED)
	public Button buttonInfo;
	@FXML
	public Text statusLabel;
	@FXML
	@RequiredState(JposState.ENABLED)
	public CheckBox freezeEvents;
	BaseJposControl service;

	static String statistics = "";

	@FXML
	public void handleOpen(ActionEvent e) {
		try {
			if (logicalName.getValue() != null && !logicalName.getValue().isEmpty()) {
				service.open(logicalName.getValue());
				RequiredStateChecker.invokeThis(this, service);
				// buttonClaim.setDisable(false);
				System.out.println(service.getState());
				setStatusLabel();
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

	@FXML
	public void handleClaim(ActionEvent e) {
		try {
			service.claim(0);
			RequiredStateChecker.invokeThis(this, service);
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to claim \"" + logicalName.getSelectionModel().getSelectedItem()
							+ "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleRelease(ActionEvent e) {
		try {
			service.release();
			if (deviceEnabled.isSelected()) {
				deviceEnabled.setSelected(false);
			}

			RequiredStateChecker.invokeThis(this, service);
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to release \"" + logicalName.getSelectionModel().getSelectedItem()
							+ "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleClose(ActionEvent e) {
		try {
			service.close();
			if (!deviceEnabled.isDisable()) {
				deviceEnabled.setSelected(false);
			}

			RequiredStateChecker.invokeThis(this, service);
			setStatusLabel();
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to close \"" + logicalName.getSelectionModel().getSelectedItem()
							+ "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleOCE(ActionEvent e) {
		handleOpen(e);
		handleClaim(e);
	}

	public void handleInfo(ActionEvent e) {

	}

	public void handleStatistics(ActionEvent e) {

	}

	// Method to parse the String XML and print the data for the
	// handleStatistics function
	public static void printStatistics(Node e, String tab) {
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
			FirmwareUpdateDlg dlg = new FirmwareUpdateDlg(service);
			dlg.setVisible(true);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Exception: " + e2.getMessage(), "Failed",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleFreezeEvents(ActionEvent e) {
		try {
			service.setFreezeEvents(freezeEvents.selectedProperty().getValue());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	/**
	 * Set StatusLabel corresponding to the Device Status
	 */
	private void setStatusLabel() {
		if (service.getState() == JposConst.JPOS_S_IDLE) {
			statusLabel.setText("JPOS_S_IDLE");
		}

		if (service.getState() == JposConst.JPOS_S_CLOSED) {
			statusLabel.setText("JPOS_S_CLOSED");
		}

		if (service.getState() == JposConst.JPOS_S_BUSY) {
			statusLabel.setText("JPOS_S_BUSY");
		}

		if (service.getState() == JposConst.JPOS_S_ERROR) {
			statusLabel.setText("JPOS_S_ERROR");
		}
	}

	protected void setUpLogicalNameComboBox(String devCategory) {
		if (!LogicalNameGetter.getLogicalNamesByCategory(devCategory).isEmpty()) {
			logicalName.setItems(LogicalNameGetter.getLogicalNamesByCategory(devCategory));
		}
	}

}
